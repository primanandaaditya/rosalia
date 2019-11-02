function ucwords (str) {
	return (str.toLowerCase() + '').replace(/^([a-z])|\s+([a-z])/g, function ($1) {
		return $1.toUpperCase();
	});
}

function hari_tgl_indo(tanggal) {
	var day = ["Minggu", "Senin", "Selasa", "Rabu", "Kamis", "Jum'at", "Sabtu"];
	var month = ['Januari', 'Februari', 'Maret', 'April', 'Mei', 'Juni', 'Juli', 'Agustus', 'September', 'Oktober', 'November', 'Desember' ];
	var date = new Date(tanggal).getDate();	  
	var _day = new Date(tanggal).getDay();
	var _month = new Date(tanggal).getMonth();
	var _years = new Date(tanggal).getYear();

	var day = day[_day];
	var month = month[_month];

	var years = (_years < 1000) ? _years + 1900 : _years;

	return day +', '+ date +' '+ month +' '+ years ;
}

function text_tgl_indo(tanggal) {
	var month = ['Januari', 'Februari', 'Maret', 'April', 'Mei', 'Juni', 'Juli', 'Agustus', 'September', 'Oktober', 'November', 'Desember' ];
	var date = new Date(tanggal).getDate();
	var _month = new Date(tanggal).getMonth();
	var _years = new Date(tanggal).getYear();

	var month = month[_month];

	var years = (_years < 1000) ? _years + 1900 : _years;

	return date +' '+ month +' '+ years ;
}
function formatAngka(angka){
    var rev     = parseInt(angka, 10).toString().split('').reverse().join('');
    var rev2    = '';
    for(var i = 0; i < rev.length; i++){
        rev2  += rev[i];
        if((i + 1) % 3 === 0 && i !== (rev.length - 1)){
            rev2 += '.';
        }
    }
    return rev2.split('').reverse().join('');
}
function randomString(length, chars) {
    var mask = '';
    if (chars.indexOf('a') > -1) mask += 'abcdefghijklmnopqrstuvwxyz';
    if (chars.indexOf('A') > -1) mask += 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
    if (chars.indexOf('#') > -1) mask += '0123456789';
    if (chars.indexOf('!') > -1) mask += '~`!@#$%^&*()_+-={}[]:";\'<>?,./|\\';
    var result = '';
    for (var i = length; i > 0; --i) result += mask[Math.floor(Math.random() * mask.length)];
    return result;
}
function next_date(date_str) {
	var parts = date_str.split("-");
	  var dt = new Date(
		parseInt(parts[0], 10),      // year
		parseInt(parts[1], 10) - 1,  // month (starts with 0)
		parseInt(parts[2], 10)       // date
	  );
	  dt.setDate(dt.getDate() + 1);
	  parts[0] = "" + dt.getFullYear();
	  parts[1] = "" + (dt.getMonth() + 1);
	  if (parts[1].length < 2) {
		parts[1] = "0" + parts[1];
	  }
	  parts[2] = "" + dt.getDate();
	  if (parts[2].length < 2) {
		parts[2] = "0" + parts[2];
	  }
	  return parts.join("-");
}

function date_dis(date_str) {
	var parts = date_str.split("/");
	  var dt = new Date(
		    // date
	  );
	  dt.setDate(dt.getDate() + 1);
	  parts[0] = "" + dt.getFullYear();
	  parts[1] = "" + (dt.getMonth() + 1);
	  if (parts[1].length < 2) {
		parts[1] = "0" + parts[1];
	  }
	  parts[2] = "" + dt.getDate();
	  if (parts[2].length < 2) {
		parts[2] = "0" + parts[2];
	  }
	  return parts.join("-");
}

function prev_date_new(date_str){
	var parts = date_str.split("/");
	  var dt = new Date(
			date_str
	  );
	  dt.setDate(dt.getDate() - 1);
	  parts[0] = "" + dt.getFullYear();
	  parts[1] = "" + (dt.getMonth() + 1);
	  if (parts[1].length < 2) {
		parts[1] = "0" + parts[1];
	  }
	  parts[2] = "" + dt.getDate();
	  if (parts[2].length < 2) {
		parts[2] = "0" + parts[2];
	  }
	  return parts.join("-");

}


function prev_date(date_str) {
	var parts = date_str.split("-");
	  var dt = new Date(
		// parseInt(parts[0], 10),      // year
		// parseInt(parts[1], 10) - 1,  // month (starts with 0)
		// parseInt(parts[2], 10)       // date
	  );
	  dt.setDate(dt.getDate() + 1);
	  parts[0] = "" + dt.getFullYear();
	  parts[1] = "" + (dt.getMonth() + 1);
	  if (parts[1].length < 2) {
		parts[1] = "0" + parts[1];
	  }
	  parts[2] = "" + dt.getDate();
	  if (parts[2].length < 2) {
		parts[2] = "0" + parts[2];
	  }
	  return parts.join("-");
}