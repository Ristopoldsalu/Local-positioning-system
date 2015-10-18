function populateRoomList(rooms) {
    $.each(rooms, function (i, room) {
        if (room.roomTypeId == 1) {
            $('#roomIdSelector').append($('<option>', {
                value: room.roomId,
                text : room.roomName + ' ' + room.roomNumber
            }));
        }
    });
}

function queryRoomsList(buildingId, contextPath) {
    $.ajax({
        url: contextPath + '/rooms',
        data: {buildingId: String(buildingId)},
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        success: function(response) {
            populateRoomList(response);
        },
        error: function() {
            console.log("Failed to query rooms list for building id " + buildingId + ".");
        }
    });
}