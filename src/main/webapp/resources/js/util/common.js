function getParameter(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === undefined ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

function getDateString(date) {
    let month = date.getMonth() + 1;
    let day = date.getDate();

    if (month < 10) {
        month = '0' + month;
    }
    if (day < 10) {
        day = '0' + day;
    }

    return `${date.getFullYear()}.${month}.${day}.`;
}

function changeReserveTypeToName(type) {
    switch(type) {
        case "A":
            return "성인";
        case "Y":
            return "청소년";
        case "B":
            return "유아";    
        case "S":
            return "세트";
        case "D":
            return "장애인";
        case "C":
            return "지역주민";
        case "E":
            return "얼리버드";
        case "D":
            return "평일";
        case "V":
            return "VIP";
        default:
            return type;
    }
}

function changePricePattern(price) {
    return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
}

function isDateOver(date) {
    const nowDate = new Date();

    if (new Date(date) < nowDate) {
        return true;
    }
    return false;
}

function getProductPeriod(reservationDate, startDay, endDay) {
    const week = ["일", "월", "화", "수", "목", "금", "토"];

    const startDate = new Date(reservationDate);
    startDate.setDate(startDate.getDate() + startDay);

    const endDate = new Date(reservationDate);
    endDate.setDate(endDate.getDate() + endDay);
        
    return `${getDateString(startDate)}(${week[startDate.getDay()]}) ~ ${getDateString(endDate)}(${week[endDate.getDay()]})`;
}