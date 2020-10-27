$(document).ready(function () {

    $('.table .eBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text(); //return New or Edit

        if (text === 'Edit') {
            $.get(href, function (user, status) {
                $('.myForm #id').val(user.id);
                $('.myForm #firstName').val(user.firstName);
                $('.myForm #lastName').val(user.lastName);
                $('.myForm #age').val(user.age);
                $('.myForm #username').val(user.username);
                $('.myForm #password').val(user.password);
                $('.myForm #role').val(user.role);
            });
            $('.myForm #editModal').modal();
        } else {
            $('.myForm #id').val('');
            $('.myForm #firstName').val('');
            $('.myForm #lastName').val('');
            $('.myForm #editModal').modal();
        }
    });

    $('.table .delBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $('#deleteModal #delRef').attr('href', href);
        $('#deleteModal').modal();
    });
});
