var monthNames = ["1月", "2月", "3月", "4月", "5月", "6月",
    "7月", "8月", "9月", "10月", "11月", "12月"
];
var dayNames = ["周日, ", "周一, ", "周二, ", "周三, ", "周四, ", "周五, ", "周六, "]

var newDate = new Date();
newDate.setDate(newDate.getDate());
$('#Date').html( dayNames[newDate.getDay()] +
		newDate.getFullYear() +'年'+ ' ' + monthNames[newDate.getMonth()]+  + newDate.getDate()  + '日 ' ) ;
