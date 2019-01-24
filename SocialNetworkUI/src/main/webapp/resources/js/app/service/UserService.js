/**
 * Created by Александр on 07.11.2016.
 */
var UserService = (function () {

    var methods = {
        loadAdvert: function (advertId, async, callback, errorback) {
            $.ajax({
                url: "/api/advert/" + advertId,
                method: "GET",
                async: async,
                success: function (data) {
                    callback(data);
                },
                error: function () {
                    if(errorback == undefined) {
                        console.error("cannot load advert");
                    } else {
                        errorback();
                    }
                }
            });
        },
        loadFriends: function (advertId, async, callback, errorback) {
            $.ajax({
                url: "/api/advert/" + advertId,
                method: "GET",
                async: async,
                success: function (data) {
                    callback(data);
                },
                error: function () {
                    if(errorback == undefined) {
                        console.error("cannot load advert");
                    } else {
                        errorback();
                    }
                }
            });
        },
        addFriends: function (friendId) {
            $.ajax({
                url: "/api/users/friends/"+ friendId,
                method: "GET",
                async: true
            });
        },
        createChat: function (friendId) {
            $.ajax({
                url: "/api/users/chats/"+ friendId,
                method: "GET",
                async: true
            });
        },
        sendMessage: function (message, chatId, msgTable) {
            var msgInfo = {chatId: chatId, message: message};
            $.ajax({
                url: "/api/chats/messages",
                type: "POST",
                data: JSON.stringify(msgInfo),
                contentType : "application/json",
                async: true,
                success: function (data) {
                    msgTable.bootstrapTable('refresh');
                }
            });
        },
        loadPossibleFriends: function ( async, callback, errorback) {
            $.ajax({
                url: "/users/colleagues",
                method: "GET",
                async: async,
                success: function (data) {
                    callback(data);
                },
                error: function () {
                    if(errorback == undefined) {
                        console.error("cannot load advert");
                    } else {
                        errorback();
                    }
                }
            });
        },
        saveAdvert: function (advert, callback, errorcallback) {
            $.ajax({
                url: "/api/advert",
                type: "POST",
                data: JSON.stringify(advert),
                dataType : 'json',
                contentType : "application/json",
                success: function (data) {
                    callback(data);
                },
                error: function (error) {
                    errorcallback(error);
                }
            });
        },
        loadFilteringPageWithOrderedAdverts: function (orderCriteria, searchCriteria, callback) {
            var criteria = {orderCriteria: orderCriteria, searchCriteria: searchCriteria};
            $.ajax({
                url: "/api/advert/page/search",
                type: "POST",
                data: JSON.stringify(criteria),
                dataType : 'json',
                contentType : "application/json",
                success: function (data) {
                    callback(data);
                },
                error: function () {
                    console.error("cannot load page with adverts");
                }
            });

        }

    };

    return methods;

}());
