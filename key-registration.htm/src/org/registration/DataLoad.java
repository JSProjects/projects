package org.registration;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.xml.rpc.ServiceException;
import javax.xml.soap.SOAPException;

import org.apache.axis.message.SOAPHeaderElement;

import com.netsuite.webservices.platform.common_2010_1.CustomRecordSearchBasic;
import com.netsuite.webservices.platform.common_2010_1.CustomRecordSearchRowBasic;
import com.netsuite.webservices.platform.core_2010_1.CustomFieldList;
import com.netsuite.webservices.platform.core_2010_1.CustomFieldRef;
import com.netsuite.webservices.platform.core_2010_1.ListOrRecordRef;
import com.netsuite.webservices.platform.core_2010_1.Passport;
import com.netsuite.webservices.platform.core_2010_1.Record;
import com.netsuite.webservices.platform.core_2010_1.RecordList;
import com.netsuite.webservices.platform.core_2010_1.RecordRef;
import com.netsuite.webservices.platform.core_2010_1.SearchColumnCustomField;
import com.netsuite.webservices.platform.core_2010_1.SearchColumnCustomFieldList;
import com.netsuite.webservices.platform.core_2010_1.SearchColumnStringCustomField;
import com.netsuite.webservices.platform.core_2010_1.SearchDateField;
import com.netsuite.webservices.platform.core_2010_1.SearchResult;
import com.netsuite.webservices.platform.core_2010_1.SearchRow;
import com.netsuite.webservices.platform.core_2010_1.SearchRowList;
import com.netsuite.webservices.platform.core_2010_1.StringCustomFieldRef;
import com.netsuite.webservices.platform.core_2010_1.types.RecordType;
import com.netsuite.webservices.platform.core_2010_1.types.SearchDateFieldOperator;
import com.netsuite.webservices.platform.faults_2010_1.ExceededRecordCountFault;
import com.netsuite.webservices.platform.faults_2010_1.ExceededRequestLimitFault;
import com.netsuite.webservices.platform.faults_2010_1.ExceededRequestSizeFault;
import com.netsuite.webservices.platform.faults_2010_1.ExceededUsageLimitFault;
import com.netsuite.webservices.platform.faults_2010_1.InvalidCredentialsFault;
import com.netsuite.webservices.platform.faults_2010_1.InvalidSessionFault;
import com.netsuite.webservices.platform.faults_2010_1.UnexpectedErrorFault;
import com.netsuite.webservices.platform.messages_2010_1.Preferences;
import com.netsuite.webservices.platform.messages_2010_1.SearchPreferences;
import com.netsuite.webservices.platform_2010_1.NetSuiteBindingStub;
import com.netsuite.webservices.platform_2010_1.NetSuitePortType;
import com.netsuite.webservices.platform_2010_1.NetSuiteServiceLocator;
import com.netsuite.webservices.setup.customization_2010_1.CustomRecord;
import com.netsuite.webservices.setup.customization_2010_1.CustomRecordSearch;
import com.netsuite.webservices.setup.customization_2010_1.CustomRecordSearchAdvanced;
import com.netsuite.webservices.setup.customization_2010_1.CustomRecordSearchRow;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class DataLoad implements Job {

	private static RecordRef snInstallBaseRef;

	static String RECORD_SN = System.getProperty(
			"netsuite.sn.installed.base.internal.id", "3");

	static KeyDAO dao = new KeyDAO();

	public static NetSuitePortType getSoapHeader() throws ServiceException,
			SOAPException {
		NetSuiteServiceLocator service = new NetSuiteServiceLocator();
		service.setNetSuitePortEndpointAddress("https://webservices.netsuite.com/services/NetSuitePort_2010_1");
		service.setMaintainSession(true);
		NetSuitePortType port = service.getNetSuitePort();
		setPreferences(port);

		return port;
	}

	/**
	 * Sets the Preferences and search preferences for an operation by adding
	 * the preferences to the SOAP header
	 *
	 * @param port
	 *            -
	 * @throws SOAPException
	 *             -
	 */
	protected static void setPreferences(NetSuitePortType port)
			throws SOAPException {

		SOAPHeaderElement userPassportHeader = new SOAPHeaderElement(
				"urn:messages.platform.webservices.netsuite.com", "passport");
		Passport passport = prepareLoginPassport();
		userPassportHeader.setObjectValue(passport);
		SOAPHeaderElement searchPrefHeader = new SOAPHeaderElement(
				"urn:messages.platform.webservices.netsuite.com",
				"searchPreferences");
		SearchPreferences searchPrefs = new SearchPreferences();
		searchPrefs.setPageSize(100);
		searchPrefs.setBodyFieldsOnly(false);
		searchPrefs.setReturnSearchColumns(true);
		searchPrefHeader.setObjectValue(searchPrefs);
		SOAPHeaderElement platformPrefHeader = new SOAPHeaderElement(
				"urn:messages.platform.webservices.netsuite.com", "preferences");
		Preferences pref = new Preferences();
		pref.setIgnoreReadOnlyFields(true);
		platformPrefHeader.setObjectValue(pref);
		NetSuiteBindingStub stub = (NetSuiteBindingStub) port;
		stub.setTimeout(1000 * 60 * 60 * 2);
		stub.clearHeaders();
		stub.setHeader(userPassportHeader);
		stub.setHeader(searchPrefHeader);
		stub.setHeader(platformPrefHeader);
	}

	public static Passport prepareLoginPassport() {
		RecordRef role = new RecordRef();
		role.setInternalId("3");
		Passport passport = new Passport();
		passport.setEmail("jshalom@aerohive.com");
		passport.setPassword("Netsuite123$");
		passport.setAccount("681261");
		passport.setRole(role);

		return passport;
	}

	public static void main(String[] args) throws ServiceException,
			SOAPException, ExceededRequestSizeFault, InvalidCredentialsFault,
			ExceededUsageLimitFault, ExceededRequestLimitFault,
			ExceededRecordCountFault, UnexpectedErrorFault,
			InvalidSessionFault, RemoteException, SQLException {

	}

	private static String convertToSerialNumbers(NetSuitePortType port,
			CustomRecordSearchRowBasic basic, boolean b) {

		String serialnumber = null;
		if (basic != null) {
			SearchColumnCustomFieldList customFieldList = basic
					.getCustomFieldList();
			SearchColumnCustomField[] customFields = customFieldList
					.getCustomField();
			for (SearchColumnCustomField customField : customFields) {
				if (customField instanceof SearchColumnStringCustomField) {
					SearchColumnStringCustomField stringCustomFieldRef = (SearchColumnStringCustomField) customField;
					String internalId = stringCustomFieldRef.getInternalId();

					if (internalId.equalsIgnoreCase("custrecord_serial_no")) {
						serialnumber = stringCustomFieldRef.getSearchValue();

					}

				}
			}
		}
		return serialnumber;
	}

	public static SearchResult SyncNetSuite(NetSuitePortType port)
			throws ExceededRequestSizeFault, InvalidCredentialsFault,
			ExceededUsageLimitFault, ExceededRequestLimitFault,
			ExceededRecordCountFault, UnexpectedErrorFault,
			InvalidSessionFault, RemoteException {

		CustomRecordSearchAdvanced ad = new CustomRecordSearchAdvanced();
		ad.setSavedSearchId("3701");
		CustomRecordSearch ts = new CustomRecordSearch();
		CustomRecordSearchBasic tsb = new CustomRecordSearchBasic();

		snInstallBaseRef = new RecordRef();
		snInstallBaseRef.setInternalId(RECORD_SN);
		snInstallBaseRef.setType(RecordType.customRecordType);

		tsb.setRecType(snInstallBaseRef);
		
		Calendar lastSync = dao.getLastSyncTime();
				/*Calendar.getInstance();
		// lastSync.add(Calendar.DATE, -1);
		lastSync.set(Calendar.HOUR, 0);
		lastSync.set(Calendar.MINUTE, 0);
		lastSync.set(Calendar.SECOND, 0);
		lastSync.setTimeInMillis(lastSync.getTimeInMillis());*/

		SearchDateField lastModifiedField = new SearchDateField();
		lastModifiedField.setOperator(SearchDateFieldOperator.onOrAfter);
		lastModifiedField.setSearchValue(lastSync);
		tsb.setLastModified(lastModifiedField);

		ts.setBasic(tsb);
		ad.setCriteria(ts);

		SearchResult searchResult = port.search(ad);

		return searchResult;

	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		NetSuitePortType port = null;
		List<String> sn = new ArrayList<String>();
		try {
			port = getSoapHeader();
			SearchResult searchResult = SyncNetSuite(port);

			String searchId = searchResult.getSearchId();
			Integer totalRecords = searchResult.getTotalRecords();
			Integer totalPages = searchResult.getTotalPages();
			if (totalRecords != null && totalRecords > 0 && totalPages != null) {
				for (int pageIndex = searchResult.getPageIndex(); pageIndex <= totalPages; pageIndex++) {
					if (pageIndex != 1) {
						searchResult = port.searchMoreWithId(searchId,
								pageIndex);
					}
					SearchRowList rowlist = searchResult.getSearchRowList();
					SearchRow[] row = rowlist.getSearchRow();
					if (row != null && row.length > 0) {
						for (SearchRow record : row) {
							CustomRecordSearchRow snRecord = (CustomRecordSearchRow) record;
							CustomRecordSearchRowBasic basic = snRecord
									.getBasic();
							String s = convertToSerialNumbers(port, basic, true);
							sn.add(s);
						}
					}
				}
			}
			dao.save(sn);
		} catch (ServiceException | SOAPException | SQLException
				| RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
