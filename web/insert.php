<html>
    <body>
        <?php
        $con = mysql_connect("jdbc:derby://localhost:1527/sample", "app", "app");
        if (!$con) {
            die('Could not connect: ' . mysql_error());
        }
        mysql_select_db("cis_id", $con);
        $sql = "INSERT INTO SEARCH_CRITERIA (EMAIL_TO, CRITERIA_URL)
VALUES
('$_POST[fname]','$_POST[lname]')";
        if (!mysql_query($sql, $con)) {
            die('Error: ' . mysql_error());
        }
        echo "1 record added";
        mysql_close($con)
        ?>
    </body>
</html>