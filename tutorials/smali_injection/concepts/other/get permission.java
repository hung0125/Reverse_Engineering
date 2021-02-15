private void getPer()
	{
		ActivityCompat.requestPermissions(MainActivity.this,
										  new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
										  1);
	}
