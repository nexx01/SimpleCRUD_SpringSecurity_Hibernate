function exampleOnclick(btn) {
    var name = btn.innerHTML;
    var exampleModal = getExampleModal();

    // Init the modal if it hasn't been already.
    if (!exampleModal) { exampleModal = initExampleModal(); }

    var html =
        '<div class="modal-header">' +
        '<h5 class="modal-title" id="exampleModalLabel">Modal title</h5>' +
        '<button type="button" class="close" data-dismiss="modal" aria-label="Close">' +
        '<span aria-hidden="true">&times;</span>' +
        '</button>' +
        '</div>' +
        '<div class="modal-body edit-content">' +
        name +

        '</div>' +
        '<div class="modal-footer">' +
        '<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>' +
        '<button type="button" class="btn btn-primary">Save changes</button>' +
        '</div>';

    setExampleModalContent(html);

    // Show the modal.
    jQuery(exampleModal).modal('show');

}

function getExampleModal() {
    return document.getElementById('exampleModal');
}

function setExampleModalContent(html) {
    getExampleModal().querySelector('.modal-content').innerHTML = html;
}

function initExampleModal() {
    var modal = document.createElement('div');
    modal.classList.add('modal', 'fade');
    modal.setAttribute('id', 'exampleModal');
    modal.setAttribute('tabindex', '-1');
    modal.setAttribute('role', 'dialog');
    modal.setAttribute('aria-labelledby', 'exampleModalLabel');
    modal.setAttribute('aria-hidden', 'true');
    modal.innerHTML =
        '<div class="modal-dialog" role="document">' +
        '<div class="modal-content"></div>' +
        '</div>';
    document.body.appendChild(modal);
    return modal;
}