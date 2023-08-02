queryWrapper.lt（）——小于

queryWrapper.le（）——小于等于

queryWrapper.gt（）——大于

queryWrapper.ge（）——大于等于

queryWrapper.eq（）——等于

queryWrapper.ne（）——不等于

queryWrapper.betweeen（“age”,10,20）——age在值10到20之间

queryWrapper.notBetweeen（“age”,10,20）——age不在值10到20之间

queryWrapper.like（“属性”,“值”）——模糊查询匹配值‘%值%’

queryWrapper.notLike（“属性”,“值”）——模糊查询不匹配值‘%值%’

queryWrapper.likeLeft（“属性”,“值”）——模糊查询匹配最后一位值‘%值’

queryWrapper.likeRight（“属性”,“值”）——模糊查询匹配第一位值‘值%’

queryWrapper.isNull（）——值为空或null

queryWrapper.isNotNull（）——值不为空或null

queryWrapper.in（“属性”，条件，条件 ）——符合多个条件的值

queryWrapper.notIn(“属性”，条件，条件 )——不符合多个条件的值

queryWrapper.or（）——或者

queryWrapper.and（）——和

queryWrapper.orderByAsc(“属性”)——根据属性升序排序

queryWrapper.orderByDesc(“属性”)——根据属性降序排序

queryWrapper.inSql(“sql语句”)——符合sql语句的值

queryWrapper.notSql(“sql语句”)——不符合SQL语句的值

queryWrapper.esists（“SQL语句”）——查询符合SQL语句的值

queryWrapper.notEsists（“SQL语句”）——查询不符合SQL语句的值

