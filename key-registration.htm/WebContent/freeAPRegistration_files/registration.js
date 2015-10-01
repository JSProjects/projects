(function($) {

	var sendRequest,
		submitForm,
		validate;
	
	checkSerial = function(serial) {
		var def = $.Deferred();
		if(serial.length != 14 || /\D/g.test(serial)) {
			$('#serial-field').closest('tr.form-group').addClass('has-error invalid');
			$('#serial-error').removeClass('hidden');
			def.resolve(false);
			return def.promise();
		} else {
			$('#serial-field').closest('tr.form-group').removeClass('has-error invalid');
			$('#serial-error').addClass('hidden');
			def.resolve(true);
		}
	
		sendRequest('GET', '/key-registration/rest/keys/validate/'+encodeURIComponent(serial)).then(function(data) {
			var d =data;
			if(d=='1') {
				$('#serial-field').closest('tr.form-group').addClass('has-error');
				$('#serial-error').removeClass('hidden');
				def.resolve(false);
				return def.promise();
			} else if(d=='0') {
				$('#serial-field').closest('tr.form-group').removeClass('has-error invalid fail');
				$('#serial-error').addClass('hidden');
				def.resolve(true);
			} else {
				$('#serial-field').closest('tr.form-group').addClass('has-error fail');
				$('#serial-error').removeClass('hidden');
				def.resolve(false);
				return def.promise();
			}
		});
		
		return def.promise();
	};

	checkEmail = function(email) {
		var def = $.Deferred();
		if(!/[a-zA-Z0-9_\-.+]+@[a-zA-Z0-9_\-.]+\.[a-zA-Z0-9_\-]+/.test(email)) {
			$('#email-field').closest('tr.form-group').addClass('has-error invalid');
			$('#email-error').removeClass('hidden');
			def.resolve(false);
			return def.promise();
		} else {
			$('#email-field').closest('tr.form-group').removeClass('has-error invalid');
			$('#email-error').addClass('hidden');
			def.resolve(true);
		}
		return def.promise();
	};

	submitForm = function() {
		console.log('Testing console');
		var $submit = $('#submit-button');
		$submit.prop("disabled", true);
		var data = {
			firstName: $('#first-name-field').val(),
			lastName: 	$('#last-name-field').val(),
			email: 		$('#email-field').val(),
			company: 	$('#company-name-field').val(),
			serialNumber: $('#serial-field').val()
		};
		sendRequest('POST', '/key-registration/rest/keys/dataprocessor', data).then(function(success) {
			if(success !== false) {
				window.location.href = 'confirmation.htm';
			} else {
				$submit.prop("disabled", false);
			}
		});
		return false;
	};
	
	function formToJSON() {
		return JSON.stringify({
			"firstName": $('#first-name-field').val(), 
			"lastName": $('#last-name-field').val(), 
			"email": $('#email-field').val(),
			"company": $('#company-name-field').val(),
			"serialNumber": $('#serial-field').val()
			});
	}

	sendRequest = function(method, url, data) {
		var def;
		def = $.Deferred();
		$.ajax({
			url: url,
			cache: false,
			type: method.toUpperCase(),
			contentType: 'application/json',
			data: formToJSON(),
			dataType: "json",
			success: function(data) {
				def.resolve(data);
			},
			error: function() {
				def.resolve(false);
			}
		});
		return def.promise();
	};

	validate = function(currentField) {
		var def = $.Deferred();
		var valid = true;

		if(!currentField) {
			$('tr.form-group:not(.validation-with-callback)').removeClass('has-error');
		}
		var selector = currentField || '.required-field';
		$(selector).each(function(idx, ele) {
			var $ele = $(ele);
			if($ele.is('select') && $ele.val() == 'Please Select') {
				$ele.closest('tr.form-group').addClass('has-error');
				valid = false;
			} else if($ele.is('input') && $ele.val().length < 1) {
				$ele.closest('tr.form-group').addClass('has-error');
				valid = false;
			} else {
				$ele.closest('tr.form-group:not(.validation-with-callback)').removeClass('has-error');
			}

		});

		if($('tr.form-group.has-error').length > 0) {
			valid = false;
		}

		if(valid == false) {
			def.resolve(false);
		} else if(currentField === false) {
			var email = $('#email-field').val();
			var serial = $('#serial-field').val();
			$.when(checkEmail(email), checkSerial(serial)).then(function(email, serial) {
				if(email && serial) {
					def.resolve(true);
				} else {
					def.resolve(false);
				}
			});
		}

		return def.promise();
	};

	$('.required-field').on('blur', function() {
		validate(this);
	});

	$('#email-field').on('blur', function(event) {
		var email = $(this).val();
        checkEmail(email);
	});

	$('#serial-field').on('blur', function(event) {
		var serial = $(this).val();
		checkSerial(serial);
	});

	$('select.required-field').on('change', function() {
		validate(this);
	});

/*	$('#country-name-field').on('change', function() {
		if($(this).val() != 'United States') {
			$('#state-name-field').prop('disabled', true).val('NA');
		} else {
			$('#state-name-field').prop('disabled', false).val('Please Select');
		}
	});*/

	$('#registration-form').on('submit', function(event) {
		event.preventDefault();
		event.stopPropagation();
		return false;
	});

	$('#submit-button').on('click', function() {
		$(this).prop('disabled', true);
		$.when(validate(false)).then(function(valid) {
			if(valid) {
				submitForm();
			} else {
				$('#submit-button').prop('disabled', false);
			}
		}, function(valid) {
			$('#submit-button').prop('disabled', false);
		});
	});

})(jQuery);
