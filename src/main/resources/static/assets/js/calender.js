$(document).ready(function () {
    $(function () {
        $('.datepicker').datepicker({
            format: 'yyyy-mm-dd',
            autoclose: true,
            startDate: '0d',
            language: "kr",


        });
        $('#datepicker').datepicker('setDate', 'today');
    });

    $('.cell').click(function () {
        $('.cell').removeClass('select');
        $(this).addClass('select');
        var time = $('.select').text();

        $('#dp2').val(time);
    });
});