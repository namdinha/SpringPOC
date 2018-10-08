"use strict";

function test(){
    $.ajax({
        method: "GET",
        url: "/calendar/getTeamCalendar",
        success: displayCalendar
    });
    function displayCalendar(calendars) {
        $.ajax({
            method: "GET",
            url: "/calendar/getMemberShifts",
            success: displayShifts
        });
        function displayShifts(shifts){
            var data = initCalendar();
            calendars.forEach(function (interval) {
                console.log(interval);
            })
            return data;
        }
    }
    function initCalendar() {
        var date = new Date();
        var data = {};

        for(var i = 0; i < 10; i++){
            data[date.getFullYear()+i] = {};
            for(var j = 0; j < 12; j++){
                data[date.getFullYear()+i][j+1] = {};
            }
        }
        return data;
    }

};

var data = test();

var calendar = new Calendar(
    "calendarContainer", // id of html container for calendar
    "small", // size of calendar, can be small | medium | large
    [
        "Wednesday", // left most day of calendar labels
        3 // maximum length of the calendar labels
    ],
    [
        "#E91E63", // primary color
        "#C2185B", // primary dark color
        "#FFFFFF", // text color
        "#F8BBD0" // text dark color
    ]
);

// initializing a new organizer object, that will use an html container to create itself
var organizer = new Organizer(
    "organizerContainer", // id of html container for calendar
    calendar, // defining the calendar that the organizer is related to
    data // giving the organizer the static data that should be displayed
);



// // function that creates dummy data for demonstration
// function createDummyData() {
//   var date = new Date();
//   var data = {};
//
//   for(var i = 0; i < 10; i++){
//     data[date.getFullYear()+i] = {};
//     for(var j = 0; j < 12; j++){
//       data[date.getFullYear()+i][j+1] = {};
//     }
//   }
//
//   for (var i = 0; i < 50; i++) {
//     var d = new Date(date.getFullYear(), date.getMonth(), date.getDate()+i);
//
//     try{
//         data[d.getFullYear()][d.getMonth()+1][d.getDate()].push({
//             startTime: i,
//             endTime: d.getMonth(),
//             text: d.getDate()
//         });
//     }
//     catch(e) {
//         data[d.getFullYear()][d.getMonth()+1][d.getDate()] = [];
//         data[d.getFullYear()][d.getMonth()+1][d.getDate()].push({
//             startTime: i,
//             endTime: d.getMonth(),
//             text: d.getDate()
//         });
//     }
//
//   }
//   return data;
// }

// creating the dummy static data
// var data = createDummyData();
//
// // initializing a new calendar object, that will use an html container to create itself
// var calendar = new Calendar(
//   "calendarContainer", // id of html container for calendar
//   "small", // size of calendar, can be small | medium | large
//   [
//     "Wednesday", // left most day of calendar labels
//     3 // maximum length of the calendar labels
//   ],
//   [
//     "#E91E63", // primary color
//     "#C2185B", // primary dark color
//     "#FFFFFF", // text color
//     "#F8BBD0" // text dark color
//   ]
// );
//
// // initializing a new organizer object, that will use an html container to create itself
// var organizer = new Organizer(
//   "organizerContainer", // id of html container for calendar
//   calendar, // defining the calendar that the organizer is related to
//   data // giving the organizer the static data that should be displayed
// );