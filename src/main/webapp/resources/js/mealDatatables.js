var ajaxUrl = 'ajax/profile/meals/';
var datatableApi;

function updateTable() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + 'filter',
        data: $('#filter').serialize(),
        success: updateTableByData
    });
    return false;
}

$(function () {
    datatableApi = $('#datatable').DataTable({
        "ajax": {
            "url": ajaxUrl,
            "dataSrc": ""
        },
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "dateTime",
                "render": function (date, type, row) {
                    if (type == 'display') {
                        var dateObject = new Date(date);
                        return '<span>' + dateObject.toISOString().substring(0, 16).replace("T", " ") + '</span>';
                    }
                    return date;
                }
            },
            {
                "data": "description"
            },
            {
                "data": "calories"
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderEditBtn
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderDeleteBtn
            }
        ],
        "order": [
            [
                0,
                "desc"
            ]
        ],
        "createdRow": function (row, data, dataIndex) {
            //$(row).css("color", data.exceed? "red": "green");
            //$(row).addClass(data.exceed? "exceeded": "normal");
            $(row).toggleClass(data.exceed? "exceeded": "normal");
        },
        "initComplete": makeEditable
    });

    $('#filter').submit(function () {
        updateTable();
        return false;
    });

    $('#dateTime').datetimepicker({
        format:'Y-m-d H:i'
    });
    $('#startDate').datetimepicker({
        timepicker:false,
        format:'Y-m-d'
    });
    $('#endDate').datetimepicker({
        timepicker:false,
        format:'Y-m-d'
    });
    $('#startTime').datetimepicker({
        datepicker:false,
        format:'H:i'
    });
    $('#endTime').datetimepicker({
        datepicker:false,
        format:'H:i'
    });
});
