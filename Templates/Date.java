// https://javarush.ru/groups/posts/1941-kak-ne-poterjatjhsja-vo-vremeni--datetime-i-calendar
// https://javarush.ru/quests/lectures/questsyntaxpro.level16.lecture01
// https://docs.oracle.com/javase/8/docs/api/java/text/SimpleDateFormat.html

Date date = new Date(); // текущая дата


// 13.09.2013 5:04:50
SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy HH:mm:ss"); 
String str = "13.09.2013 5:04:50"; 
Date parsingDate = ft.parse(str); 
