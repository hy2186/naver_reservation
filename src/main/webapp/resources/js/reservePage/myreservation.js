$(document).ready(function() {
    const myReservationPageLoader = new MyReservationPageLoader();
    myReservationPageLoader.requestReservationData();
});

function MyReservationPageLoader() {
}
MyReservationPageLoader.prototype = {
    "requestReservationData": function() {
        $.ajax({
            type: "GET",
            url: "/reservation/api/reservations?reservationEmail=" + $(".viewReservation").text(),
            caches: false
        })
        .done(function(data) {
            this.initPageView(data);
        }.bind(this))
        .fail(function(response) {
            alert(`에러발생 : ${response.status} - ${response.responseText}`);
        });
    },

    "initPageView": function(reservationData) {
        const reservationListView = new ReservationListView();
        const reservationCountView = new ReservationCountView();

        reservationListView.init(reservationData);
        reservationCountView.updateView();
    }
}

function ReservationCountView() {
}
ReservationCountView.prototype = {
    "updateView": function() {
        this.updateTicketCount();
        this.updateTicketList();
    },

    "updateTicketCount": function() {
        const confirmedCount = $("#confirmed_ticket_list .card_item").length;
        const usedCount = $("#used_ticket_list .card_item").length;
        const canceledCount = $("#canceled_ticket_list .card_item").length;

        $("#confirmed_ticket_count").text(confirmedCount);
        $("#used_ticket_count").text(usedCount);
        $("#canceled_ticket_count").text(canceledCount);
        $("#total_ticket_count").text(confirmedCount + usedCount + canceledCount);
    },

    "updateTicketList": function() {
        const noTicketInfo = $("#no_ticket_info").text();

        const confirmedCount = $("#confirmed_ticket_list .card_item").length;
        const usedCount = $("#used_ticket_list .card_item").length;
        const canceledCount = $("#canceled_ticket_list .card_item").length;

        if (confirmedCount <= 0) {
            $("#confirmed_ticket_list").append(noTicketInfo);
        }
        if (usedCount <= 0) {
            $("#used_ticket_list").append(noTicketInfo);
        }
        if (canceledCount <= 0) {
            $("#canceled_ticket_list").append(noTicketInfo);
        }
    }
}

function ReservationListView() {
}
ReservationListView.prototype = {
    "init": function(reservationData) {
        this.registerHelpters();
        this.addReservationCard(reservationData);
        this.activateCancelButton();
    },

    "registerHelpters": function() {
        Handlebars.registerHelper("isDateOver", function(reservationDate, options) {
            if (isDateOver(reservationDate)) {
                return options.fn(this);
            }
            return options.inverse(this);
        });
        Handlebars.registerHelper("reservationDate", function(reservationDate) {
            return getDateString(new Date(reservationDate));
        });
        Handlebars.registerHelper("changePricePattern", function(price) {
            return changePricePattern(price);
        });
    },

    "addReservationCard": function(reservationData) {
        const confirmedTemplate = Handlebars.compile($("#confirmed_ticket_template").text());
        const usedTemplate = Handlebars.compile($("#used_ticket_template").text());
        const canceledTemplate = Handlebars.compile($("#canceled_ticket_template").text());
        
        $("#confirmed_ticket_list").append(confirmedTemplate(reservationData));
        $("#used_ticket_list").append(usedTemplate(reservationData));
        $("#canceled_ticket_list").append(canceledTemplate(reservationData));
    },

    "activateCancelButton": function() {
        const reservationCountView = new ReservationCountView();
        const $confiremedReservationList = $("#confirmed_ticket_list");
        const $canceledReservationList = $("#canceled_ticket_list");

        const self = this;
        $confiremedReservationList.on("click", ".booking_cancel", function() {
            if (confirm("예약을 취소하시겠습니까?")) {
                const $card = $(this).parents(".card_item");
                const reservationId = $card.find(".booking_number").data("reservation_id");
                
                if (self.requestCancelReservation(reservationId) === 200) {
                    $confiremedReservationList.remove($card);
                    $canceledReservationList.append($card);
                    $(this).remove();
                    reservationCountView.updateView();
                }
            }       
        });
    },

    "requestCancelReservation": function(reservationId) {
        let status;
        $.ajax({
            type: "PUT",
            url: "/reservation/api/reservations/" + reservationId,
            async: false,
        })
        .done(function(data, message, response) {
            status = response.status;
        })
        .fail(function(response) {
            alert(`에러발생 : ${response.status} - ${response.responseText}`);
        });
        return status;
    },
}