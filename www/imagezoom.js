var imagezoom = {
    createEvent: function(title, location, notes, startDate, endDate, imageUrl,successCallback, errorCallback) {
        cordova.exec(
            successCallback, // success callback function
            errorCallback, // error callback function
            'Imagezoom', // mapped to our native Java class called "CalendarPlugin"
            'triggerZoom', // with this action name
            [{                  // and this array of custom arguments to create our entry
                "title": title,
                "description": notes,
                "eventLocation": location,
                "startTimeMillis": startDate.getTime(),
                "endTimeMillis": endDate.getTime(),
                "imageUrl":imageUrl
                
            }]
        ); 
    }
};
module.exports = imagezoom;
