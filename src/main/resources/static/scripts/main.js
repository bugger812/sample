/**
 * Created by Sigal on 5/21/2016.
 */
$(document).ready(function () {

    $("#addNewPackageButton").click(function () {
        var packageName = $("#packageName").val();
        var packagePrice = $("#packagePrice").val();
        var ticketsInPackage = $("#ticketsInPackage").val();
        var commission = $("#commission").val();
        $.post("/add-new-package.json?", "name=" + packageName + "&&price=" + packagePrice + "&&tickets=" + ticketsInPackage + "&&commission=" + commission, addNewPackageResponse);
    });

    $("#packageName, #packagePrice, #ticketsInPackage, #commission").change(function () {
        markSaved(false);
    });

    $(".ammount-of-packages, #paymentDate").change(function () {
        markSaved(false);
    });

    $("#newClientName").change(function () {
        markSaved(false);
    });

    $("#depositAmountCheck,#depositAmountCach, #depositCommentCheck, #depositCommentCash").change(function () {
        markSaved(false);
    });

    $("#incomeAmount, #outcomeAmount, #incomeOutcomeDetails").change(function () {
        markSaved(false);
    });

    $("#cuponsSum, #refundSum, #refundDate").change(function () {
        markSaved(false);
    });

    $("#dailyStartAmount, #dailySummaryComment").change(function () {
        markSaved(false);
    });

    $(".cash-details").change(function () {
        markSaved(false);
    });

    function addNewPackageResponse(data, status) {
        var element = "";
        if (data.error) {
            element = "#addedPackageFailure";
        } else {
            $("input").val("");
            element = "#addedPackageSuccess";
        }
        fadeInOutElement(element);

    }


    function fadeInOutElement(element) {
        $(element).fadeIn(1000).delay(1000).fadeOut(4000);
    }

    function fadeInElement(element) {
        $(element).fadeIn(1000);
    }

    function fadeOutElement(element) {
        $(element).fadeOut(4000);
    }


    $(".hishgad-package-details").click(function () {
        $("#addOrderTable").show("slow");
        var packageOid = $(this).attr("oid");
        $("#package-row-" + packageOid).show();

    });

    $(".remove-package-row-button").click(function () {
        var packageOid = $(this).attr("oid");
        $("#amountOfPackages-" + packageOid).val("0");
        $("#package-row-" + packageOid).hide();

    });

    $(".ammount-of-packages").keyup(function () {
        var packageOid = $(this).attr("oid");
        var netoPrice = $(this).attr("neto");
        var brutoPrice = $(this).attr("bruto");
        var amount = $(this).val();
        var total = amount * netoPrice;
        var totalBruto = amount * brutoPrice;
        $(this).attr("bruto-sum",totalBruto);
        $("#order-sum-"+ packageOid).text(total);
        recalculateTotalOrder();
        $("#orderSummary").show();
    });

    function recalculateTotalOrder() {
        var text = "";
        var sum = 0;
        $(".ammount-of-packages").each(function( index ) {
            if ($(this).val() != 0) {
                var price = $("#order-sum-" + $(this).attr("oid")).text();
                text += $(this).val();
                text += " * ";
                text += $(this).attr("name");
                text += " = ";
                text += price;
                sum += parseInt(price);
                text += "</br>";
            }
        });
        text += "סך הכל: ";
        text += sum;
        $("#totalOrderSum").val(sum);
        $("#currentOrderPayment").text(sum);
        text += "</br>";
        if (sum != 0) {
            $("#approveOrder").show();
        }
        $("#orderSummaryBody").html(text);
    }

    $("#approveOrder").click(function () {
        var purchaseDate = $("#currentDate").val();
        var paymentDate = $("#paymentDate").val();
        if (paymentDate == "" || purchaseDate == "" ) {
            $("#missingDetails").show();
        } else {
            var oids = [];
            var amounts = [];
            var brutoTotal = 0;
            $(".ammount-of-packages").each(function( index ) {
                // if ($(this).val() > 0) {
                    oids.push($(this).attr("oid"));
                    amounts.push($(this).val());
                    brutoTotal += parseInt($(this).attr("bruto-sum"));
                // }
            });
            var params = "oids=" + oids + "&&amounts=" + amounts + "&&totalSum=" + $("#totalOrderSum").val() + "&&purchaseDate=" + purchaseDate + "&&paymentDate=" +paymentDate + "&&bruto=" + brutoTotal;
            $.post("/add-new-order.json?", params , addNewOrderResponse);
        }

    });

    function addNewOrderResponse(data, status){
        var element = "";
        if (data.error) {
            element = "#addedOrderFailure";
        } else {
            element = "#addedOrderSuccess";
            $("#currentOrderDetails").show("slow");
        }
        fadeInOutElement(element);
    }

    $("#purchaseDate").change(function () {
        $("#currentPurchaseDate").text($(this).val());
    });

    $("#paymentDate").change(function () {
        $("#currentPaymentDate").text($(this).val());
        $("#missingDetails").hide();
    });

    $(".lotto-sales").keyup(function () {
        if ($(this).val() == "") {
            $(this).val(0);
        }
        markSaved(false);
        var totalSales = 0;
        var totalWins = 0;
        var totalCommissions = 0;

        var lottoSales = $("#lottoSales").val();
        var lottoWins = $("#lottoWins").val();
        var lottoCommission = (lottoSales * 0.07).toFixed(3);
        $("#lottoCommission").text(lottoCommission);
        totalSales += parseFloat(lottoSales);
        totalWins += parseFloat(lottoWins);
        totalCommissions += parseFloat(lottoCommission);


        var extraSales = $("#extraSales").val();
        var extraWins = $("#extraWins").val();
        var extraCommission = (extraSales * 0.07).toFixed(3);
        $("#extraCommission").text(extraCommission);
        totalSales += parseFloat(extraSales);
        totalWins += parseFloat(extraWins);
        totalCommissions += parseFloat(extraCommission);


        var specialSales = $("#specialSales").val();
        var specialWins = $("#specialWins").val();
        var specialCommission = (specialSales * 0.07).toFixed(3);
        $("#specialCommission").text(specialCommission);
        totalSales += parseFloat(specialSales);
        totalWins += parseFloat(specialWins);
        totalCommissions += parseFloat(specialCommission);


        var chanceSales = $("#chanceSales").val();
        var chanceWins = $("#chanceWins").val();
        var chanceCommission = (chanceSales * 0.07).toFixed(3);
        $("#chanceCommission").text(chanceCommission);
        totalSales += parseFloat(chanceSales);
        totalWins += parseFloat(chanceWins);
        totalCommissions += parseFloat(chanceCommission);


        var oneTwoThreeSales = $("#oneTwoThreeSales").val();
        var oneTwoThreeWins = $("#oneTwoThreeWins").val();
        var oneTwoThreeCommission = (oneTwoThreeSales * 0.07).toFixed(3);
        $("#oneTwoThreeCommission").text(oneTwoThreeCommission);
        totalSales += parseFloat(oneTwoThreeSales);
        totalWins += parseFloat(oneTwoThreeWins);
        totalCommissions += parseFloat(oneTwoThreeCommission);


        var sevenSales = $("#sevenSales").val();
        var sevenWins = $("#sevenWins").val();
        var sevenCommission = (sevenSales * 0.07).toFixed(3);
        $("#sevenCommission").text(sevenCommission);
        totalSales += parseFloat(sevenSales);
        totalWins += parseFloat(sevenWins);
        totalCommissions += parseFloat(sevenCommission);

        var hishgadWins = $("#hishgadWins").val();
        totalWins += parseFloat(hishgadWins);

        var remaining = totalSales - totalWins;
        $("#remaining").text(remaining.toFixed(3));
        $("#commissions").text((totalCommissions).toFixed(3));
        $("#summary").text((remaining - totalCommissions).toFixed(3));

    });

    $("#saveLottoForm").click(function () {
        var params = "";
        params += "lottoSales=";
        params += $("#lottoSales").val();
        params += "&&lottoWins=";
        params += $("#lottoWins").val();

        params += "&&extraSales=";
        params += $("#extraSales").val();
        params += "&&extraWins=";
        params += $("#extraWins").val();

        params += "&&specialSales=";
        params += $("#specialSales").val();
        params += "&&specialWins=";
        params += $("#specialWins").val();

        params += "&&chanceSales=";
        params += $("#chanceSales").val();
        params += "&&chanceWins=";
        params += $("#chanceWins").val();

        params += "&&oneTwoThreeSales=";
        params += $("#oneTwoThreeSales").val();
        params += "&&oneTwoThreeWins=";
        params += $("#oneTwoThreeWins").val();

        params += "&&sevenSales=";
        params += $("#sevenSales").val();
        params += "&&sevenWins=";
        params += $("#sevenWins").val();

        params += "&&hishgadWins=";
        params += $("#hishgadWins").val();

        params += "&&currentDate="
        params += $("#currentDate").val();

        $.post("/save-daily-sales.json?", params, saveDailySalesResponse);
    });

    function saveDailySalesResponse(data, status) {
        var element = data.error ? "#saveDailySalesFailure" : "#saveDailySalesSuccess";
        fadeInOutElement(element);
    }

    $(".new-amount").keyup(function () {
        markSaved(false);
        oids = [];
        remaining = [];
        $("#saveHishgadSales").show();
        var curOid = $(this).attr("oid");
        var curOldAmount = $("#old-amount-" + curOid).text();
        var curNewAmount = $(this).val();
        if (curNewAmount == "") {
            curNewAmount = curOldAmount;
        }
        var curDif = parseInt(curOldAmount) - parseInt(curNewAmount);
        $("#difference-" + curOid).text(curDif);
        var sum = parseInt($("#previousSum").val());
        $(".new-amount").each(function( index ) {
            var oid = $(this).attr("oid");
            var dif = parseInt($("#difference-" + oid).text());
            var newAmount = parseInt($("#new-amount-" + oid).val());
            if (dif != 0) {
                oids.push(oid);
                remaining.push(newAmount);
                var price = parseInt($("#price-" + oid).text());
                sum += (dif*price);
            }
            $("#sumHishgadSales").text(sum);
        });
    });

    $("#saveHishgadSales").click(function () {
        var params = "";
        params +="currentDate=";
        params += $("#currentDate").val();
        params += "&&sum=";
        params += $("#sumHishgadSales").text();
        params += "&&oids=";
        params += oids;
        params += "&&remaining=";
        params += remaining;
        $.post("/save-daily-hishgad-sales.json?", params, saveDailyHishgadSalesResponse);
    });

    function saveDailyHishgadSalesResponse(data, status) {
        oids = [];
        remaining = [];
        var element = "";
        if (data.error) {
            element = "#AddedHishgadDailySalesFailure";
        } else {
            element = "#AddedHishgadDailySalesSuccess";
        }
        fadeInOutElement(element);

    }

    $("#saveCurrentDate").click(function () {
        var params = "";
        params +="currentDate=";
        params += $("#currentDate").val();
        window.location="/dashboard?" + params;
    });

    $("#addNewClient").click(function () {
        var name = $("#newClientName").val();
        var exists = false;
        $(".client-name").each(function( index ) {
            if ($(this).text() == name) {
                exists = true;
            }
        });
        if (!exists){
            var params = "";
            params +="clientName=";
            params += $("#newClientName").val();
            $.post("/add-new-client.json?", params, addNewClientResponse);
        } else {
            fadeInOutElement("#clientExists");
        }

    });

    function addNewClientResponse(data, status) {
        if (data.error) {
            var element = "#addedClientFailure";
            fadeInElement(element);
        } else {
            $("#newClientName").val("");
            $("#addOrderForm").hide("slow");
            $("#currentClientName").text(data.name);
            $("#currentClient").show("slow");
            $("#addNewClient").hide("slow")
            $("#newClientName").hide("slow")
        }
    }

    $(".daily-debt").keyup(function () {
        markSaved(false);
        $("#saveDailyDebts").show("slow");
        var oid = $(this).attr("client-oid");
        var before = parseFloat($("#before-debt-" + oid).text());
        var value = parseFloat($(this).val());
        var after = before + value;
        $("#after-debt-" + oid).text(after);
    });

    $(".client-daily-debt-details").click(function () {
        var oid = $(this).attr("client-oid");
        $("#change-debt-" + oid).attr("show","true");
        $("#dailyDebts").show("slow");
        $("#debt-row-" + oid).show("slow");
    });

    $("#saveDailyDebts").click(function () {
        var beforeArray = [];
        var changeArray = [];
        var clientsArray = [];
        var debtsArray = [];
        var oid = $(this).attr("oid");
        $(".daily-debt").each(function( index ) {
            if ($(this).attr("show") == "true") {
                var before = $(this).attr("before");
                var change = $(this).val();
                var clientOid = $(this).attr("client-oid");
                var debtOid = $(this).attr("debt-oid");
                beforeArray.push(before);
                changeArray.push(change);
                clientsArray.push(clientOid);
                debtsArray.push(debtOid);
            }
        });


        var params = "before=" + beforeArray + "&&change=" + changeArray + "&&clients=" + clientsArray;
        params +="&&currentDate=";
        params += $("#currentDate").val();
        $.post("/save-daily-debts.json?", params , saveDailyDebtsResponse);

    });

    function saveDailyDebtsResponse(data, status) {
        var element = "";
        if (data.error) {
            if (data.illegalChange) {
                element = "#AddedDailyDebtsFailureIllegal";

            } else {
                element = "#AddedDailyDebtsFailure";
            }
        } else {
            element = "#AddedDailyDebtsSuccess";
        }
        fadeInOutElement(element);
    }


    $("#saveDeposit").click(function () {
        var params = "currentDate=";
        params += $("#currentDate").val();
        params += "&&amountCheck=";
        params += $("#depositAmountCheck").val();
        params += "&&amountCash=";
        params += $("#depositAmountCash").val();
        params += "&&checkComment=";
        params += $("#depositCommentCheck").val();
        params += "&&cashComment=";
        params += $("#depositCommentCash").val();
        $.post("/save-deposit.json?", params , saveDepositResponse);

    });

    function saveDepositResponse(data, status) {
        var element = "";
        if (data.error) {
            element = "#AddedDepositFailure";
        } else {
            element = "#AddedDepositSuccess";
        }
        fadeInOutElement(element);
    }


    $("#saveIncomeOutcome").click(function () {
        var params = "currentDate=";
        params += $("#currentDate").val();
        params += "&&income=";
        params += $("#incomeAmount").val();
        params += "&&outcome=";
        params += $("#outcomeAmount").val();
        params += "&&details=";
        params += $("#incomeOutcomeDetails").val();
        $.post("/save-income-outcome.json?", params , saveIncomeOutcomeResponse);

    });

    function saveIncomeOutcomeResponse(data, status) {
        var element = "";
        if (data.error) {
            element = "#AddedIncomeOutcomeFailure";
        } else {
            element = "#AddedIncomeOutcomeSuccess";
        }
        fadeInOutElement(element);
    }

    $("#saveCredit").click(function () {
        var refundSum = $("#refundSum").val();
        var refundDate = $("#refundDate").val();
        var cuponsSum = $("#cuponsSum").val();
        if (refundSum != "" && refundDate == "") {
            fadeInOutElement("#AddedCreditFailureNoDate");
        } else {
            var params = "currentDate=";
            params += $("#currentDate").val();
            params += "&&cuponsSum=";
            params += cuponsSum == "" ? 0 : cuponsSum;
            params += "&&refundSum=";
            params += refundSum == "" ? 0 : refundSum;
            params += "&&refundDateString=";
            params += refundDate == "" ? "": refundDate;
            $.post("/save-credit.json?", params , saveCreditResponse);
        }
    });



    function saveCreditResponse(data, status) {
        var element = "";
        if (data.error) {
            element = "#AddedCreditFailure";
        } else {
            element = "#AddedCreditSuccess";
        }
        fadeInOutElement(element);
    }

    $("#saveDailySummary").click(function () {
        var params = "currentDate=";
        params += $("#currentDate").val();
        params += "&&dailyStartAmount=";
        params += $("#dailyStartAmount").val();
        params += "&&dailySummaryComment=";
        params += $("#dailySummaryComment").val();
        $.post("/save-daily-summary.json?", params , saveDailySummaryResponse);
    });

    function saveDailySummaryResponse(data, status) {
        var element = "";
        if (data.error) {
            element = "#AddedDailySummaryFailure";
        } else {
            element = "#AddedDailySummarySuccess";
        }
        fadeInOutElement(element);
    }

    $("#dailyStartAmount").keyup(function () {
        var endAmount = parseFloat($(this).val());
        endAmount += parseFloat($("#dailyLottoSales").text());
        endAmount -= parseFloat($("#dailyLottoWins").text());
        endAmount += parseFloat($("#dailyHishgadSales").text());
        endAmount -= parseFloat($("#dailyCredit").text());
        endAmount -= parseFloat($("#dailyDebts").text());
        $("#dailyEndAmount").text(endAmount.toFixed(2));
    });

    $("#changeDate").click(function () {
        $(this).val()
        var params = "currentDate=";
        params += $("#currentDate").val();
        window.location="/change-date?" + params;
    });

    $("#showDebtsHistory").click(function () {
        if ($("#debtsHistory").is(':visible')) {
            $("#debtsHistory").fadeOut("slow");
            $(this).text("הצג היסטוריה");
        } else {
            $("#debtsHistory").fadeIn("slow");
            $(this).text("הסתר היסטוריה");
        }
    });

    function calcLottoPayments() {
        markSaved(false);
        var total = 0;
        var totalRefunds = 0;
        var totalSummaries = 0;
        var totalOrders = 0;
        var others = parseFloat($("#lotto-payments-other").val());
        total += others;

        var refunds = [];
        var summaries = [];
        var orders = [];
        $(".lotto-payments").each(function( index ) {
            if ($(this).prop( "checked" )) {
                var type = parseInt($(this).attr("checkbox-type"));
                var oid = $(this).attr("oid");
                var sum = parseFloat($(this).attr("sum"));

                switch (type) {
                    case 0:
                        refunds.push(oid);
                        totalRefunds -= sum;
                        total -= sum;
                        break;
                    case 1:
                        summaries.push(oid);
                        totalSummaries += sum;
                        total += sum;
                        break;
                    case 2:
                        orders.push(oid);
                        totalOrders += sum;
                        total += sum;
                        break;
                }
            }
        });
        $("#sumCreditRefund").text(totalRefunds.toFixed(2));
        $("#sumSales").text(totalSummaries.toFixed(2));
        $("#sumOrders").text(totalOrders.toFixed(2));
        $("#totalPayment").text(total.toFixed(2));
    }

    $(".lotto-payments").change(function () {
        calcLottoPayments();
    });

    $("#lotto-payments-other").keyup(function () {
        calcLottoPayments();
    });


    $("#saveLottoPayments").click(function () {
        var total = 0;
        var totalRefunds = 0;
        var totalReports = 0;
        var totalOrders = 0;
        var others = parseFloat($("#lotto-payments-other").val());
        total += others;

        var refunds = [];
        var reports = [];
        var orders = [];
        $(".lotto-payments").each(function( index ) {
            if ($(this).prop( "checked" )) {
                var type = parseFloat($(this).attr("checkbox-type"));
                var oid = $(this).attr("oid");
                var sum = parseFloat($(this).attr("sum"));
                total += sum;

                switch (type) {
                    case 0:
                        refunds.push(oid);
                        totalRefunds += sum;
                        break;
                    case 1:
                        reports.push(oid);
                        totalReports += sum;
                        break;
                    case 2:
                        orders.push(oid);
                        totalOrders += sum;
                        break;
                }
            }
        });
        var params = "";
        params +="currentDate=";
        params += $("#currentDate").val();
        params += "&refunds=";
        params += refunds;
        params += "&reports=";
        params += reports;
        params += "&orders=";
        params += orders;
        params += "&total=";
        params += total;
        params += "&totalReports=";
        params += totalReports;
        params += "&totalRefunds=";
        params += totalRefunds;
        params += "&totalOrders=";
        params += totalOrders;
        params += "&others=";
        params += others;

        $.post("/save-lotto-payment.json?", params , saveLottoPaymentResponse);
    });

    function saveLottoPaymentResponse(data, status) {
        var element = "";
        if (data.error) {
            element = "#savedLottoPaymentFailure";
        } else {
            $("input").val("");
            element = "#savedLottoPaymentSuccess";
        }
        fadeInOutElement(element);
    }

    $("#saveBankIncomeOutcome").click(function () {
        var params = "currentDate=";
        params += $("#currentDate").val();
        params += "&&income=";
        params += $("#incomeAmount").val();
        params += "&&outcome=";
        params += $("#outcomeAmount").val();
        params += "&&details=";
        params += $("#incomeOutcomeDetails").val();
        $.post("/save-bank-income-outcome.json?", params , saveBankIncomeOutcomeResponse);

    });

    function saveBankIncomeOutcomeResponse(data, status) {
        var element = "";
        if (data.error) {
            element = "#AddedIncomeOutcomeFailure";
        } else {
            element = "#AddedIncomeOutcomeSuccess";
        }
        fadeInOutElement(element);
    }

    $("#showDailySalesHistory").click(function () {
        if ($("#lottoSalesHistory").is(':visible')) {
            $("#lottoSalesHistory").fadeOut("slow");
            $(this).text("הצג היסטוריה");
        } else {
            $("#lottoSalesHistory").fadeIn("slow");
            $(this).text("הסתר היסטוריה");
        }
    });

    var $table = $('#balanceTable table');

    $table.floatThead({
        //useAbsolutePositioning: true,
        scrollContainer: function ($table) {
            return $table.closest('#balanceTable');
        }
    });

    $(".save-button").click(function () {
        markSaved(true);
    });

    $("#stayToSave").click(function () {
        $("#unsaved-changes-message").hide();
    });

    $("#moveWithoutSaving").click(function () {
        window.location = $(this).attr("url");
    });

    $("#printButton").click(function () {
        var prtContent = document.getElementById("print");
        var WinPrint = window.open('', '', 'left=0,top=0,width=800,height=900,toolbar=0,scrollbars=0,status=0');
        WinPrint.document.write(prtContent.innerHTML);
        WinPrint.document.close();
        WinPrint.focus();
        WinPrint.print();
        WinPrint.close();
    });

    $("#saveTheChanges").click(function () {
        $('.save-button').trigger('click');
        window.location = $(this).attr("url");
    });

    $(".cash-details").keyup(function () {
        var newVal = $(this).val();
        if (newVal == "") {
            $(this).text(0);
            newVal = 0;
        }
        var amount = newVal;
        var sum = $(this).attr("sum");
        var value = 0;
        if (sum == -1) {
            value = parseFloat(amount);
            $("#equity-check").text(value);

        } else {
            value = parseFloat(amount) * parseFloat(sum);
            $("#equity-" + sum).text(value);
        }
        calcCashEquity();

    });


    function calcCashEquity() {
        var sum = 0;
        $(".cash-equity").each(function( index ) {
            var val = parseFloat($(this).text());
            sum += val;
        });
        $("#cash-sum").text(sum);
        var endDay = parseFloat($("#daily-summary-sum").text());
        var dif = sum - endDay;
        // $("#difference").text(dif);
        // if(dif < 0) {
        //     $("#difference").removeClass("green-text-color");
        //     $("#difference").addClass("red-text-color");
        // } else {
        //     $("#difference").removeClass("red-text-color");
        //     $("#difference").addClass("green-text-color");
        // }
        var difElement = $("#difference");
        dif < 0 ? difElement.removeClass("green-text-color").addClass("red-text-color") : difElement.removeClass("red-text-color").addClass("green-text-color");
        difElement.text(dif);
    }

    $("#saveCashDetails").click(function () {
        var params = "currentDate=";
        params += $("#currentDate").val();
        params += "&&amount1=";
        params += $("#cash-details-1").val();
        params += "&&amount5=";
        params += $("#cash-details-5").val();
        params += "&&amount10=";
        params += $("#cash-details-10").val();
        params += "&&amount20=";
        params += $("#cash-details-20").val();
        params += "&&amount50=";
        params += $("#cash-details-50").val();
        params += "&&amount100=";
        params += $("#cash-details-100").val();
        params += "&&amount200=";
        params += $("#cash-details-200").val();
        params += "&&amountChecks=";
        params += $("#cash-details-checks").val();
        $.post("/save-cash-details.json?", params , saveCashDetailsResponse);
    });

    function saveCashDetailsResponse(data, status) {
        var element = "";
        if (data.error) {
            element = "#addedCashFailure";
        } else {
            element = "#addedCashSuccess";
        }
        fadeInOutElement(element);

    }

    $("#showHideCashHistory").click(function () {
        if ($("#cashHistory").is(':visible')) {
            $("#cashHistory").fadeOut("slow");
            $(this).text("הצג היסטוריה");
        } else {
            $("#cashHistory").fadeIn("slow");
            $(this).text("הסתר היסטוריה");
        }
    });

    $("#resetLottoForm").click(function () {
        $(".lotto-sales").val("0");
        $('#summary, #remaining, #commissions, .lotto-commissions').text("0");
        markSaved(false);
    });

    $(".salary-type").click(function () {
        if ($("#feeBySales").is(':visible')) {
            $("#feeBySales").hide("fast");
            $(".fee-by-sales").hide("fast");
            $("#feeByPurchase").show("fast");
            $(".fee-by-purchase").show("fast");
            $("#sumByPurchase").show("fast");
            $("#sumBySales").hide("fast");
            $(".sum-by-purchase").show("fast");
            $(".sum-by-sales").hide("fast");
            $("#monthlyHishgadPurchaseSalary").show("fast");
            $("#monthlyHishgadSalesSalary").hide("fast");
            $("#monthlyPurchaseSalary").show("fast");
            $("#monthlySalesSalary").hide("fast");
        } else {
            $("#feeBySales").show("fast");
            $(".fee-by-sales").show("fast");
            $("#feeByPurchase").hide("fast");
            $(".fee-by-purchase").hide("fast");
            $("#sumByPurchase").hide("fast");
            $("#sumBySales").show("fast");
            $(".sum-by-purchase").hide("fast");
            $(".sum-by-sales").show("fast");
            $("#monthlyHishgadPurchaseSalary").hide("fast");
            $("#monthlyHishgadSalesSalary").show("fast");
            $("#monthlyPurchaseSalary").hide("fast");
            $("#monthlySalesSalary").show("fast");
        }
        // $(".lotto-sales").val("0");
        // $('#summary, #remaining, #commissions, .lotto-commissions').text("0");
        // markSaved(false);
    });

    $(".remove-client").click(function () {
        var oid = $(this).attr("oid");
        var debt = parseFloat($("#debt-" + oid).text());
        if (debt != 0) {
            fadeInOutElement("#removeClientFailure");
        } else {
            var params = "oid=" + oid;
            $.post("/remove-client.json?", params , removeClientResponse);
        }
    });

    function removeClientResponse (data, status) {
        var element = "";
        if (data.error) {
            element = "#removeClientFailure";
        } else {
            var oid = data.oid;
            $("#client-row-" + oid).hide("slow");
        }
        fadeInOutElement(element);
    }

    $(".remove-package").click(function () {
        var oid = $(this).attr("oid");
        var debt = parseFloat($("#old-amount-" + oid).text());
        if (debt != 0) {
            fadeInOutElement("#removePackageFailure");
        } else {
            var params = "oid=" + oid;
            $.post("/remove-package.json?", params , removePackageResponse);
        }
    });

    function removePackageResponse (data, status) {
        var element = "";
        if (data.error) {
            element = "#removePackageFailure";
        } else {
            var oid = data.oid;
            $("#package-row-" + oid).hide("slow");
        }
        fadeInOutElement(element);
    }


    $(".package-name").dblclick(function () {
        var oid = $(this).attr("oid");
        $("#edit-package-" + oid).show("fast");
    });

    $(".edit-package-name").focusout(function () {
        changePackageName(this);
    });

    function changePackageName(element) {
        $(element).hide("fast");
        var params = "oid=";
        params += $(element).attr("oid");
        params += "&name=";
        params += $(element).val();
        $.post("/change-package-name.json?", params , changePackageNameResponse);

    }

    function changePackageNameResponse (data, status) {
        var element = "";
        if (data.error) {
            element = "#changePackageNameFailure";
        } else {
            var oid = data.oid;
            $("#package-name-" + oid).text(data.name);
        }
        fadeInOutElement(element);
    }

    $(".edit-package-name").keypress(function(e) {
        if(e.which == 13) {
            changePackageName(this);
        }
    });

    $("#runBackupJob").click(function () {
        $.post("/run-backup-job.json?", "" , runJobResponse);
    });

    $("#runNewRecordJob").click(function () {
        $.post("/run-new-record-job.json?", "" , runJobResponse);
    });

    $("#runDeactivateRecordJob").click(function () {
        $.post("/run-new-record-job.json?", "deactivate=true" , runJobResponse);
    });

    function runJobResponse() {
        alert("Job Started");
    }

    $("#refreshMaps").click(function () {
        $.post("/refresh-maps.json?", "" , runJobResponse);
    });

    $("#restoreDump").click(function () {
        $.post("/restore-dump.json?", "date=" + $("#selectedDumpDate").val() , runJobResponse);
    });

    $(".dump-date-item").click(function () {
        $("#selectedDumpDate").val($(this).text().trim());
        $("#restoreDump").attr("disabled", false);
    });




});

function checkSaved(url) {
    $("#moveWithoutSaving").attr("url",url);
    $("#saveTheChanges").attr("url",url);
    $('#unsaved-changes-message').modal('show');
    var saved = $("#changesSaved").attr("saved") == "true";
    return saved;
}


function markSaved(saved) {
    $('.save-button').attr("disabled",saved);
    $("#changesSaved").attr("saved", saved ? "true" : "false");
}


