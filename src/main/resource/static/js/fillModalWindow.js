$('.modal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget) // Button that triggered the modal
    var recipientUserId = button.data('id') // Extract info from data-* attributes
    var recipientFirstName = button.data('whatever') // Extract info from data-* attributes
    var recipientLastname = button.data('lastname') // Extract info from data-* attributes
    var recipientAge = button.data('age') // Extract info from data-* attributes
    var recipientEmail = button.data('email') // Extract info from data-* attributes
    var recipientRoles = button.data('role') // Extract info from data-* attributes
    // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
    // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
    var modal = $(this)
    modal.find('.modal-title').text('New message to ' + recipientFirstName)
    modal.find(' #user_id').val(recipientUserId)
    modal.find(' #firstNameEdit').val(recipientFirstName)
    modal.find(' #lastNameEdit').val(recipientLastname)
    modal.find(' #ageEdit').val(recipientAge)
    modal.find(' #emailEdit').val(recipientEmail)

})
