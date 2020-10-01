<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Title</title>
</head>
<body>
<table border="1">
<#include "*/header.ftl" encoding="UTF-8" ignore_missing=true >
    <tr>
        <td>用户编号</td>
        <td>用户名称</td>
    </tr>
    <#list users as user>
        <tr>
            <td>${user.id}</td>
            <td>${user.username}</td>
        </tr>
    </#list>
</table>
<#assign age=23>
<#if (age>60)>老年人
<#elseif (age>30)>中年人
<#elseif (age>20)>青少年
<#else>老年人
</#if>
<#assign gender=1>
<#switch gender>
 <#case 0>女性
     <#break>
 <#case 1>男性
      <#break>
 <#default>未知
</#switch>
<br/>
<#list ["星期一","星期二","星期三","星期四","星期五","星期六"] as x>
${x_index+1}.${x}<#if x_has_next>,</#if>
<#if x_index=4><#break></#if>
</#list>
<#assign week=["星期一","星期二","星期三","星期四","星期五"]>
<#list week as day>
${day}
</#list>
</body>
</html>