<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>계산기</title>
        <style>
            input {width: 50px;
                height: 50px;}
            .output {
                height: 50px;
                background: #e9e9e9;
                font-size: 24px;
                font-weight: bold;
                text-align: right;
                padding: 0 10px;
            }
        </style>
    </head>
    <body>
        <form action="calc2" method="post">
            <table>
                <tr>
                    <%
                        int x = 3;
                        int y = 5;
                        int sum = x + y;%>
                    <td colspan="4" class="output">결과 : <%=sum%></td>
                </tr>
                <tr>
                    <td><input type="submit" name="op" value="CE"></td>
                    <td><input type="submit" name="op" value="C"></td>
                    <td><input type="submit" name="op" value="B"></td>
                    <td><input type="submit" name="op" value="÷"></td>
                </tr>
                <tr>
                    <td><input type="submit" name="value" value="7"></td>
                    <td><input type="submit" name="value" value="8"></td>
                    <td><input type="submit" name="value" value="9"></td>
                    <td><input type="submit" name="op" value="x"></td>
                </tr>
                <tr>
                    <td><input type="submit" name="value" value="4"></td>
                    <td><input type="submit" name="value" value="5"></td>
                    <td><input type="submit" name="value" value="6"></td>
                    <td><input type="submit" name="op" value="-"></td>
                </tr>
                <tr>
                    <td><input type="submit" name="value" value="1"></td>
                    <td><input type="submit" name="value" value="2"></td>
                    <td><input type="submit" name="value" value="3"></td>
                    <td><input type="submit" name="op" value="+"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" name="value" value="0"></td>
                    <td><input type="submit" name="dot" value="."></td>
                    <td><input type="submit" name="op" value="="></td>
                </tr>
            </table>

        </form>
    </body>
</html>