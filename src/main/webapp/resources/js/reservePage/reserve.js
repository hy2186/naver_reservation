$(document).ready(function() {
    const reservePageLoader = new ReservePageLoader();
    reservePageLoader.initPage();
});

function ReservePageLoader() {
}
ReservePageLoader.prototype = {
    "initPage": function() {
        this.requestDisplayData();
    },

    "requestDisplayData": function() {
        $.ajax({
            type: "GET",
            url: "/reservation/api/products/" + getParameter("displayInfoId"),
            caches: false
        })
        .done(function(data) {
            this.initPageView(data);
        }.bind(this))
        .fail(function(response) {
            alert(`에러발생 : ${response.status} - ${response.responseText}`);
        });
    },

    "initPageView": function(displayData) {
        const displayContentView = new DisplayContentView();
        const ticketSelectView = new TicketSelectView();
        const agreementView = new AgreementView();
        const submitUnit = new SubmitUnit();

        displayContentView.init(displayData);
        ticketSelectView.init(displayData.productPrices);
        agreementView.init();
        submitUnit.activateSubmitButton(displayData.displayInfo);
    },
}

function DisplayContentView() {
}
DisplayContentView.prototype = {
    "init": function(displayData) {
        this.setReservationDate();
        this.makeHeader(displayData);
        this.makeProductImage(displayData);
        this.makeProductContent(displayData);
    },

    "setReservationDate": function() {
        const reservationDate = new Date($("#reservation_date").text());
        
        $("#reservation_date").text(getDateString(reservationDate));
        $("#reservation_date").val(reservationDate);
    },

    "makeHeader": function(displayData) {
        $("#reservation_title").text(displayData.displayInfo.productDescription);
    },

    "makeProductImage": function(displayData) {
        $("#product_image").attr("src", "/reservation/download/image/" + displayData.productImages[0].fileId);
        $("#product_image_price").append(displayData.productPrices[0].price);
        $("#product_image_opening").text(getProductPeriod($("#reservation_date").val(), 5, 10));
    },

    "makeProductContent": function(displayData) {
        $("#product_location").text(displayData.displayInfo.placeName);
        $("#product_period").text(getProductPeriod($("#reservation_date").val(), 5, 10));
        $("#opening_hour").text(displayData.displayInfo.openingHours);

        const priceDescription = displayData.productPrices.map(function(price) {
            return `${changeReserveTypeToName(price.priceTypeName)} ${changePricePattern(price.price)} 원`
        });

        $("#price_description").text(priceDescription.join(" / "));
    },
}

function TicketSelectView() {
}
TicketSelectView.prototype = {
    "init": function(productPrices) {
        this.registerHelpers();
        this.makeTicketList(productPrices);
        this.activateTicketSelect();
    },

    "registerHelpers": function() {
        Handlebars.registerHelper("priceTypeName", function(ticket) {
            return changeReserveTypeToName(ticket.priceTypeName);
        });
        Handlebars.registerHelper("changePricePattern", function(price) {
            return changePricePattern(price);
        });
    },

    "makeTicketList": function(productPrices) {
        const ticketTemplate = $("#ticket_template").text();
        const handlebarsTemplate = Handlebars.compile(ticketTemplate);
        
        $("#ticket_body").append(handlebarsTemplate(productPrices));
    },

    "activateTicketSelect": function() {
        $("#ticket_body").on("click", ".btn_plus_minus", function(event) {
            const countButton = $(event.target);
            const ticketBox = countButton.parents(".ticket_item");
            const countBox = ticketBox.find(".count_control_input");

            let currentCount = Number(countBox.val());

            if (countButton.hasClass("minus_btn")) {
                if(currentCount <= 0) {
                    return;
                }
                currentCount = currentCount - 1;
            } else {
                currentCount = currentCount + 1;
            }
            countBox.val(currentCount);

            this.changeTotalPrice(ticketBox, currentCount);
            this.toggleStyle(ticketBox, currentCount);
            this.changeTotalTicketCount();
        }.bind(this));
    },

    "changeTotalPrice": function(ticketBox, changedCount) {
        const priceBox = ticketBox.find(".price");
        const price = Number(priceBox.attr("value"));
        const totalPriceBox = ticketBox.find(".total_price");
            
        totalPriceBox.text(changePricePattern(price * changedCount));
    },

    "toggleStyle": function(ticketBox, changedCount) {
        if (changedCount > 0) {
            ticketBox.find(".minus_btn, .count_control_input").removeClass("disabled");
            ticketBox.find(".individual_price").addClass("on_color");
        } else {
            ticketBox.find(".minus_btn, .count_control_input").addClass("disabled");
            ticketBox.find(".individual_price").removeClass("on_color");
        }
    },

    "changeTotalTicketCount": function() {
        const countList = $.makeArray($(".count_control_input"));
        
        const totalTicketCount = countList.reduce(function(total, count) {
            return total += Number($(count).val());
        }, 0);

        $("#total_ticket_count").val(totalTicketCount);
        $("#total_ticket_count").text(totalTicketCount);
    },
}

function AgreementView() {
}
AgreementView.prototype = {
    "init": function() {
        this.activateFoldButton();
        this.activeToggleAgreement();
    },

    "activateFoldButton": function() {
        $(".btn_agreement").on("click", function(event) {
            const agreementBox = $(event.target).parents(".agreement");
            const buttonText = agreementBox.find(".btn_text");
            
            if(agreementBox.hasClass("open")) {
                agreementBox.removeClass("open");
                buttonText.text("보기");
            } else {
                agreementBox.addClass("open");
                buttonText.text("접기");
            }
        })
    },

    "activeToggleAgreement": function() {
        $("#chk3").on("click", function() {
            if($("#chk3").is(":checked")) {
                $(".bk_btn_wrap").removeClass("disable");
            } else {
                $(".bk_btn_wrap").addClass("disable");
            }
        });
    }
}

function ValidationUnit() {
}
ValidationUnit.prototype = {
    "isValidSubmitForm": function() {
        return this.isValidInputForm() && this.isValidTotalTicketCount() && this.isValidAgreement();
    },

    "isValidInputForm": function() {
        const isValidName = this.isValidName();
        const isValidTelephone = this.isValidTelephone();
        const isValidEmail = this.isValidEmail();

        return isValidName && isValidTelephone && isValidEmail;
    },

    "isValidTotalTicketCount": function() {
        if($("#total_ticket_count").val() > 0) {
            return true;
        }
        alert("티켓을 선택해주세요.");
        return false;
    },

    "isValidAgreement": function() {
        return $("#chk3").is(":checked");
    },

    "isValidName": function() {
        const $nameBox = $("#name");
        const name = $nameBox.val();
        const isValid = (/^[가-힣]+$/).test(name);
        
        this.showWarning(isValid, $nameBox);
        
        return isValid;
    },

    "isValidTelephone": function() {
        const $telBox = $("#tel");
        const telephone = $telBox.val();
        const isValid = (/^\d{2,3}-\d{3,4}-\d{4}$/).test(telephone);
        
        this.showWarning(isValid, $telBox);

        return isValid;
    },

    "isValidEmail": function() {
        const $emailBox = $("#email");
        const email = $emailBox.val();
        const isValid = (/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i).test(email);
        
        this.showWarning(isValid, $emailBox);

        return isValid;
    },

    "showWarning": function(isValid, $inputBox) {
        const $warning = $inputBox.next();
        if(isValid) {
            $warning.css("visibility", "hidden");
        } else {
            $warning.css("visibility", "visible");
            setTimeout(function() {
			    $warning.css("visibility", "hidden");
			}, 1500);
        }
    }
}

function SubmitUnit() {
}
SubmitUnit.prototype = {
    "activateSubmitButton": function(displayInfo) {
        const validationUnit = new ValidationUnit();
        $(".bk_btn_wrap").on("click", function() {
            if (validationUnit.isValidSubmitForm()) {
                const submitForm = this.createRequestData(displayInfo);
                this.requestSubmit(submitForm);
            }
        }.bind(this));
    },

    "createRequestData": function(displayInfo) {
        const requestData = {
            "displayInfoId": displayInfo.displayInfoId,
            "prices": this.createPrices(),
            "productId": displayInfo.productId,
            "reservationEmail": $("#email").val(),
            "reservationName": $("#name").val(),
            "reservationTelephone": $("#tel").val(),
            "reservationYearMonthDay": $("#reservation_date").val()
        };

        return requestData;
    },
    
    "createPrices": function() {
        const ticketList = $.makeArray($(".ticket_item"));
        const prices = ticketList.map(function(ticket) {
            return {
                productPriceId: $(ticket).data("product_price_id"),
                count: $(ticket).find(".count_control_input").val()
            }
        });

        return prices;
    },

    "requestSubmit": function(submitForm) {
        $.ajax({
            type: "POST",
            url: "/reservation/api/reservations",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(submitForm)
        })
        .done(function() {
            alert("예약이 완료되었습니다.");
			location.href = "/reservation";
        })
        .fail(function(response) {
            alert("에러발생 : " + response.status + response.responseText);
        });
    }
}