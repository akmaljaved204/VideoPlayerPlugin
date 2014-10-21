var imagezoom = {
    createEvent: function(imageUrl,successCallback, errorCallback) {
        cordova.exec(
            successCallback, // success callback function
            errorCallback, // error callback function
            'Imagezoom', // mapped to our native Java class called "CalendarPlugin"
            'triggerZoom', // with this action name
            [{                  // and this array of custom arguments to create our entry
                "imageUrl":imageUrl
                
            }]
        ); 
    }
};
module.exports = imagezoom;