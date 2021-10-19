const app = getApp();
module.exports = {
  // make price with 2 digits
  formatPrice(price) {
    return parseFloat(Math.round(price * 100) / 100).toFixed(2)
  },
  

  formatTime(time, reg) {
    const date = typeof time === 'string' || typeof time === 'number' ? new Date(time) : time;
    const map = {};
    map.yyyy = date.getFullYear();
    map.yy = ('' + map.yyyy).substr(2);
    map.M = date.getMonth() + 1
    map.MM = (map.M < 10 ? '0' : '') + map.M;
    map.d = date.getDate();
    map.dd = (map.d < 10 ? '0' : '') + map.d;
    map.H = date.getHours();
    map.HH = (map.H < 10 ? '0' : '') + map.H;
    map.m = date.getMinutes();
    map.mm = (map.m < 10 ? '0' : '') + map.m;
    map.s = date.getSeconds();
    map.ss = (map.s < 10 ? '0' : '') + map.s;

    return reg.replace(/\byyyy|yy|MM|M|dd|d|HH|H|mm|m|ss|s\b/g, $1 => {
      return map[$1];
    });
  },

  getId() {
    return Math.floor((1 + Math.random()) * 0x100000000).toString(16).slice(1)
  },
}