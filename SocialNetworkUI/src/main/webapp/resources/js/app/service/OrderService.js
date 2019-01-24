/**
 * Created by Александр on 13.12.2016.
 */
var OrderService = (function () {

    var methods = {
        addOrder: function (advert, userId, callback, errorback) {
            var request = {advert: advert, userId: userId};
            $.ajax({
                url: "/api/order",
                type: "POST",
                data: JSON.stringify(request),
                dataType : 'json',
                contentType : "application/json",
                async: false,
                success: function (data) {
                    callback(data);
                },
                error: function () {
                    console.error("cannot add order");
                    errorback();
                }
            });
        }

    };

    return methods;

}());
