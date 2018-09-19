(function () {
    var btn = $("#list-rooms-btn");
    btn.click(function(ev) {
        $.ajax({
            method: "GET",
            url: "/rooms/api/v1/getRooms",
            success: displayRooms
        });
    });
    function displayRooms(rooms) {
        var allRooms = $("#room-list");
        rooms.forEach(function (room) {
            var html = "<div>" + "<input type=\"button\" id=\"" + room.name + "\" value=\"" + room.name + "\"></div>";
            allRooms.append(html);
        });
        allRooms.on('click', 'input', function (ev) {
            $.ajax({
                method: "GET",
                url: "/booking/api/v1/isBooked",
                data: {roomName : ev.currentTarget.id},
                success: isRoomBooked
            });
        });
        function isRoomBooked(isBooked) {
            console.log(isBooked);
        }
    }

})();