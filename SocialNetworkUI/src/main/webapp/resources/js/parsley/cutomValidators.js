/**
 *
 * Created by Александр on 06.12.2016.
 */
window.Parsley.addValidator('realYear', {
    validateNumber: function(value, minYear) {
        var maxYear = new Date().getFullYear();
        return +value >= minYear && +value <= maxYear;
    },
    requirementType: 'integer',
    messages: {
        en: 'Invalid year.Year should be between  %s and ' + new Date().getFullYear(),
        ru: "Недопустимый год. Год должен быть между %s и " + new Date().getFullYear()
    }
});
window.Parsley.addValidator('numberAfterInput', {
    validateNumber: function(value, inputId) {
        var afterValue = +$(inputId).val();
        if (afterValue == undefined) {
            return true;
        }
        return +value >= afterValue;
    },
    messages: {
        en: 'Invalid input. Should be larger.',
        ru: "Неверный ввод. Должно быть больше."
    }
});