stage["title"];
stage.recalled("123");

var javaNetPackage = java.net;
//var URL = nashorn.Message;

var Message = Java.type('nashorn.Message');
//static method
Message.proIn();
//实例化对象
var mess = new Message();
mess.printSelf();

//讲js数组转为java数组
//var javaNames = Java.to(names, StringArray);
//讲java数组转为js数组
//var jsNames = Java.from(numbers);

var names = java.util.Arrays.asList('1' , '2');
names[0];
