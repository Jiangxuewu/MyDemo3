.class public Landroid/bb_sz/os/SystemProperties;
.super Ljava/lang/Object;
.source "SystemProperties.java"


# static fields
.field private static debug:Z = false

.field private static mCls:Ljava/lang/Class; = null
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/lang/Class",
            "<*>;"
        }
    .end annotation
.end field

.field private static mNativeGetInt:Ljava/lang/reflect/Method; = null

.field private static mNativeGetLong:Ljava/lang/reflect/Method; = null

.field private static mNativeGetString_1:Ljava/lang/reflect/Method; = null

.field private static mNativeGetString_2:Ljava/lang/reflect/Method; = null

.field private static final pidFile:Ljava/lang/String; = "/data/local/tmp/pid.prop"

.field private static final propFile:Ljava/lang/String; = "/data/local/tmp/test.prop"


# direct methods
.method static constructor <clinit>()V
    .registers 1

    .prologue
    const/4 v0, 0x0

    .line 19
    sput-object v0, Landroid/bb_sz/os/SystemProperties;->mCls:Ljava/lang/Class;

    .line 20
    sput-object v0, Landroid/bb_sz/os/SystemProperties;->mNativeGetInt:Ljava/lang/reflect/Method;

    .line 21
    sput-object v0, Landroid/bb_sz/os/SystemProperties;->mNativeGetLong:Ljava/lang/reflect/Method;

    .line 22
    sput-object v0, Landroid/bb_sz/os/SystemProperties;->mNativeGetString_1:Ljava/lang/reflect/Method;

    .line 23
    sput-object v0, Landroid/bb_sz/os/SystemProperties;->mNativeGetString_2:Ljava/lang/reflect/Method;

    .line 27
    const/4 v0, 0x1

    sput-boolean v0, Landroid/bb_sz/os/SystemProperties;->debug:Z

    return-void
.end method

.method public constructor <init>()V
    .registers 1

    .prologue
    .line 18
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method private static changed(Ljava/lang/String;)Ljava/lang/String;
    .registers 2
    .param p0, "key"    # Ljava/lang/String;

    .prologue
    .line 242
    const-string v0, "ro.build.id"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_b

    .line 243
    const-string p0, "refresh.id"

    .line 271
    .end local p0    # "key":Ljava/lang/String;
    :cond_a
    :goto_a
    return-object p0

    .line 244
    .restart local p0    # "key":Ljava/lang/String;
    :cond_b
    const-string v0, "ro.product.model"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_16

    .line 245
    const-string p0, "refresh.model"

    goto :goto_a

    .line 246
    :cond_16
    const-string v0, "ro.serialno"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_21

    .line 247
    const-string p0, "refresh.serial"

    goto :goto_a

    .line 248
    :cond_21
    const-string v0, "ro.build.version.release"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_2c

    .line 249
    const-string p0, "refresh.version"

    goto :goto_a

    .line 250
    :cond_2c
    const-string v0, "ro.build.version.sdk"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_37

    .line 251
    const-string p0, "refresh.api"

    goto :goto_a

    .line 252
    :cond_37
    const-string v0, "ro.product.manufacturer"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_42

    .line 253
    const-string p0, "refresh.manufacturer"

    goto :goto_a

    .line 254
    :cond_42
    const-string v0, "ro.product.brand"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_4d

    .line 255
    const-string p0, "refresh.brand"

    goto :goto_a

    .line 256
    :cond_4d
    const-string v0, "ro.build.product"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_5d

    const-string v0, "ro.product.name"

    invoke-virtual {v0, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_60

    .line 257
    :cond_5d
    const-string p0, "refresh.product"

    goto :goto_a

    .line 258
    :cond_60
    const-string v0, "ro.product.device"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_6b

    .line 259
    const-string p0, "refresh.device"

    goto :goto_a

    .line 260
    :cond_6b
    const-string v0, "ro.product.board"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_76

    .line 261
    const-string p0, "refresh.board"

    goto :goto_a

    .line 262
    :cond_76
    const-string v0, "ro.product.cpu.abi"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_81

    .line 263
    const-string p0, "refresh.cpuabi"

    goto :goto_a

    .line 264
    :cond_81
    const-string v0, "ro.product.cpu.abi2"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_8d

    .line 265
    const-string p0, "refresh.cpuabi2"

    goto/16 :goto_a

    .line 266
    :cond_8d
    const-string v0, "ro.hardware"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_99

    .line 267
    const-string p0, "refresh.hardware"

    goto/16 :goto_a

    .line 268
    :cond_99
    const-string v0, "qemu.sf.lcd_density"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_a9

    const-string v0, "ro.sf.lcd_density"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_a

    .line 269
    :cond_a9
    const-string p0, "refresh.densityDpi"

    goto/16 :goto_a
.end method

.method public static getFloat(Ljava/lang/String;I)F
    .registers 5
    .param p0, "key"    # Ljava/lang/String;
    .param p1, "value"    # I

    .prologue
    .line 109
    sget-boolean v0, Landroid/bb_sz/os/SystemProperties;->debug:Z

    if-eqz v0, :cond_26

    const-string v0, "sky"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "getFloat key = "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, ", value = "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    .line 110
    :cond_26
    int-to-float v0, p1

    invoke-static {p0, v0}, Landroid/bb_sz/os/SystemProperties;->getFloatValue(Ljava/lang/String;F)F

    move-result v0

    return v0
.end method

.method public static getFloatValue(Ljava/lang/String;F)F
    .registers 7
    .param p0, "key"    # Ljava/lang/String;
    .param p1, "value"    # F

    .prologue
    .line 230
    invoke-static {}, Landroid/bb_sz/os/SystemProperties;->init()V

    .line 231
    move v1, p1

    .line 232
    .local v1, "result":F
    invoke-static {p0}, Landroid/bb_sz/os/SystemProperties;->changed(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 233
    .local v0, "newKey":Ljava/lang/String;
    new-instance v2, Ljava/io/File;

    const-string v3, "/data/local/tmp/test.prop"

    invoke-direct {v2, v3}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-virtual {v2}, Ljava/io/File;->exists()Z

    move-result v2

    if-eqz v2, :cond_42

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v2

    if-nez v2, :cond_42

    const-string v2, "refresh"

    invoke-virtual {v0, v2}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v2

    if-eqz v2, :cond_42

    .line 234
    invoke-static {}, Landroid/bb_sz/os/SystemProperties;->getMockProp()Ljava/util/Properties;

    move-result-object v2

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, ""

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2, v0, v3}, Ljava/util/Properties;->getProperty(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-static {v2}, Ljava/lang/Float;->parseFloat(Ljava/lang/String;)F

    move-result v1

    .line 236
    :cond_42
    sget-boolean v2, Landroid/bb_sz/os/SystemProperties;->debug:Z

    if-eqz v2, :cond_68

    const-string v2, "sky2"

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "getFloatValue key = "

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    const-string v4, ", value = "

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-static {v2, v3}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    .line 237
    :cond_68
    return v1
.end method

.method public static getIntValue(Ljava/lang/String;I)I
    .registers 12
    .param p0, "key"    # Ljava/lang/String;
    .param p1, "value"    # I

    .prologue
    .line 201
    invoke-static {}, Landroid/bb_sz/os/SystemProperties;->init()V

    .line 202
    move v4, p1

    .line 203
    .local v4, "result":I
    invoke-static {p0}, Landroid/bb_sz/os/SystemProperties;->changed(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    .line 204
    .local v2, "newKey":Ljava/lang/String;
    new-instance v5, Ljava/io/File;

    const-string v6, "/data/local/tmp/test.prop"

    invoke-direct {v5, v6}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-virtual {v5}, Ljava/io/File;->exists()Z

    move-result v5

    if-eqz v5, :cond_42

    invoke-static {v2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v5

    if-nez v5, :cond_42

    const-string v5, "refresh"

    invoke-virtual {v2, v5}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v5

    if-eqz v5, :cond_42

    .line 206
    :try_start_23
    invoke-static {}, Landroid/bb_sz/os/SystemProperties;->getMockProp()Ljava/util/Properties;

    move-result-object v5

    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    const-string v7, ""

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v6

    invoke-virtual {v6, v4}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v6

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v5, v2, v6}, Ljava/util/Properties;->getProperty(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v5

    invoke-static {v5}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I
    :try_end_41
    .catch Ljava/lang/Exception; {:try_start_23 .. :try_end_41} :catch_8f

    move-result v4

    .line 213
    :cond_42
    :goto_42
    if-ne v4, p1, :cond_68

    sget-object v5, Landroid/bb_sz/os/SystemProperties;->mNativeGetInt:Ljava/lang/reflect/Method;

    if-eqz v5, :cond_68

    .line 215
    :try_start_48
    sget-object v5, Landroid/bb_sz/os/SystemProperties;->mNativeGetInt:Ljava/lang/reflect/Method;

    const/4 v6, 0x0

    const/4 v7, 0x2

    new-array v7, v7, [Ljava/lang/Object;

    const/4 v8, 0x0

    aput-object p0, v7, v8

    const/4 v8, 0x1

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v9

    aput-object v9, v7, v8

    invoke-virtual {v5, v6, v7}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v3

    .line 216
    .local v3, "object":Ljava/lang/Object;
    if-eqz v3, :cond_68

    instance-of v5, v3, Ljava/lang/Integer;

    if-eqz v5, :cond_68

    .line 217
    check-cast v3, Ljava/lang/Integer;

    .end local v3    # "object":Ljava/lang/Object;
    invoke-virtual {v3}, Ljava/lang/Integer;->intValue()I
    :try_end_67
    .catch Ljava/lang/Exception; {:try_start_48 .. :try_end_67} :catch_b8

    move-result v4

    .line 225
    :cond_68
    :goto_68
    sget-boolean v5, Landroid/bb_sz/os/SystemProperties;->debug:Z

    if-eqz v5, :cond_8e

    const-string v5, "sky2"

    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    const-string v7, "getIntValue key = "

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v6

    invoke-virtual {v6, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v6

    const-string v7, ", value = "

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v6

    invoke-virtual {v6, v4}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v6

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    invoke-static {v5, v6}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    .line 226
    :cond_8e
    return v4

    .line 207
    :catch_8f
    move-exception v1

    .line 208
    .local v1, "ignored":Ljava/lang/Exception;
    move v4, p1

    .line 209
    sget-boolean v5, Landroid/bb_sz/os/SystemProperties;->debug:Z

    if-eqz v5, :cond_42

    const-string v5, "sky2"

    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    const-string v7, "getIntValue err 1 key = "

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v6

    invoke-virtual {v6, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v6

    const-string v7, ", value = "

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v6

    invoke-virtual {v6, v4}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v6

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    invoke-static {v5, v6}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_42

    .line 218
    .end local v1    # "ignored":Ljava/lang/Exception;
    :catch_b8
    move-exception v0

    .line 219
    .local v0, "e":Ljava/lang/Exception;
    invoke-virtual {v0}, Ljava/lang/Exception;->printStackTrace()V

    .line 220
    move v4, p1

    .line 221
    sget-boolean v5, Landroid/bb_sz/os/SystemProperties;->debug:Z

    if-eqz v5, :cond_68

    .line 222
    const-string v5, "sky2"

    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    const-string v7, "getIntValue err 2 key = "

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v6

    invoke-virtual {v6, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v6

    const-string v7, ", value = "

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v6

    invoke-virtual {v6, v4}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v6

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    invoke-static {v5, v6}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_68
.end method

.method public static getLongValue(Ljava/lang/String;J)J
    .registers 12
    .param p0, "key"    # Ljava/lang/String;
    .param p1, "value"    # J

    .prologue
    .line 180
    invoke-static {}, Landroid/bb_sz/os/SystemProperties;->init()V

    .line 181
    move-wide v2, p1

    .line 182
    .local v2, "result":J
    invoke-static {p0}, Landroid/bb_sz/os/SystemProperties;->changed(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    .line 183
    .local v1, "newKey":Ljava/lang/String;
    new-instance v4, Ljava/io/File;

    const-string v5, "/data/local/tmp/test.prop"

    invoke-direct {v4, v5}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-virtual {v4}, Ljava/io/File;->exists()Z

    move-result v4

    if-eqz v4, :cond_42

    invoke-static {v1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v4

    if-nez v4, :cond_42

    const-string v4, "refresh"

    invoke-virtual {v1, v4}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v4

    if-eqz v4, :cond_42

    .line 184
    invoke-static {}, Landroid/bb_sz/os/SystemProperties;->getMockProp()Ljava/util/Properties;

    move-result-object v4

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    const-string v6, ""

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v5, v2, v3}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v4, v1, v5}, Ljava/util/Properties;->getProperty(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    invoke-static {v4}, Ljava/lang/Long;->parseLong(Ljava/lang/String;)J

    move-result-wide v2

    .line 187
    :cond_42
    cmp-long v4, v2, p1

    if-nez v4, :cond_64

    sget-object v4, Landroid/bb_sz/os/SystemProperties;->mNativeGetLong:Ljava/lang/reflect/Method;

    if-eqz v4, :cond_64

    .line 189
    :try_start_4a
    sget-object v4, Landroid/bb_sz/os/SystemProperties;->mNativeGetLong:Ljava/lang/reflect/Method;

    const/4 v5, 0x0

    const/4 v6, 0x2

    new-array v6, v6, [Ljava/lang/Object;

    const/4 v7, 0x0

    aput-object p0, v6, v7

    const/4 v7, 0x1

    invoke-static {p1, p2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v8

    aput-object v8, v6, v7

    invoke-virtual {v4, v5, v6}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/lang/Long;

    invoke-virtual {v4}, Ljava/lang/Long;->longValue()J
    :try_end_63
    .catch Ljava/lang/Exception; {:try_start_4a .. :try_end_63} :catch_8b

    move-result-wide v2

    .line 196
    :cond_64
    :goto_64
    sget-boolean v4, Landroid/bb_sz/os/SystemProperties;->debug:Z

    if-eqz v4, :cond_8a

    const-string v4, "sky2"

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    const-string v6, "getLongValue key = "

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v5, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    const-string v6, ", value = "

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v5, v2, v3}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    invoke-static {v4, v5}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    .line 197
    :cond_8a
    return-wide v2

    .line 190
    :catch_8b
    move-exception v0

    .line 191
    .local v0, "e":Ljava/lang/Exception;
    invoke-virtual {v0}, Ljava/lang/Exception;->printStackTrace()V

    .line 192
    move-wide v2, p1

    .line 193
    sget-boolean v4, Landroid/bb_sz/os/SystemProperties;->debug:Z

    if-eqz v4, :cond_64

    const-string v4, "sky2"

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    const-string v6, "getLongValue err key = "

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v5, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    const-string v6, ", value = "

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v5, v2, v3}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    invoke-static {v4, v5}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_64
.end method

.method public static getMockProp()Ljava/util/Properties;
    .registers 1

    .prologue
    .line 275
    const-string v0, "/data/local/tmp/test.prop"

    invoke-static {v0}, Landroid/bb_sz/os/SystemProperties;->getMockProp(Ljava/lang/String;)Ljava/util/Properties;

    move-result-object v0

    return-object v0
.end method

.method public static getMockProp(Ljava/lang/String;)Ljava/util/Properties;
    .registers 6
    .param p0, "file"    # Ljava/lang/String;

    .prologue
    .line 279
    new-instance v3, Ljava/util/Properties;

    invoke-direct {v3}, Ljava/util/Properties;-><init>()V

    .line 280
    .local v3, "prop":Ljava/util/Properties;
    const/4 v1, 0x0

    .line 282
    .local v1, "in":Ljava/io/InputStream;
    :try_start_6
    new-instance v2, Ljava/io/FileInputStream;

    invoke-direct {v2, p0}, Ljava/io/FileInputStream;-><init>(Ljava/lang/String;)V
    :try_end_b
    .catch Ljava/lang/Exception; {:try_start_6 .. :try_end_b} :catch_1b
    .catchall {:try_start_6 .. :try_end_b} :catchall_27

    .line 283
    .end local v1    # "in":Ljava/io/InputStream;
    .local v2, "in":Ljava/io/InputStream;
    :try_start_b
    invoke-virtual {v3, v2}, Ljava/util/Properties;->load(Ljava/io/InputStream;)V
    :try_end_e
    .catch Ljava/lang/Exception; {:try_start_b .. :try_end_e} :catch_36
    .catchall {:try_start_b .. :try_end_e} :catchall_33

    .line 286
    if-eqz v2, :cond_39

    .line 288
    :try_start_10
    invoke-virtual {v2}, Ljava/io/InputStream;->close()V
    :try_end_13
    .catch Ljava/io/IOException; {:try_start_10 .. :try_end_13} :catch_15

    move-object v1, v2

    .line 293
    .end local v2    # "in":Ljava/io/InputStream;
    .restart local v1    # "in":Ljava/io/InputStream;
    :cond_14
    :goto_14
    return-object v3

    .line 289
    .end local v1    # "in":Ljava/io/InputStream;
    .restart local v2    # "in":Ljava/io/InputStream;
    :catch_15
    move-exception v0

    .line 290
    .local v0, "e":Ljava/io/IOException;
    invoke-virtual {v0}, Ljava/io/IOException;->printStackTrace()V

    move-object v1, v2

    .line 291
    .end local v2    # "in":Ljava/io/InputStream;
    .restart local v1    # "in":Ljava/io/InputStream;
    goto :goto_14

    .line 284
    .end local v0    # "e":Ljava/io/IOException;
    :catch_1b
    move-exception v4

    .line 286
    :goto_1c
    if-eqz v1, :cond_14

    .line 288
    :try_start_1e
    invoke-virtual {v1}, Ljava/io/InputStream;->close()V
    :try_end_21
    .catch Ljava/io/IOException; {:try_start_1e .. :try_end_21} :catch_22

    goto :goto_14

    .line 289
    :catch_22
    move-exception v0

    .line 290
    .restart local v0    # "e":Ljava/io/IOException;
    invoke-virtual {v0}, Ljava/io/IOException;->printStackTrace()V

    goto :goto_14

    .line 286
    .end local v0    # "e":Ljava/io/IOException;
    :catchall_27
    move-exception v4

    :goto_28
    if-eqz v1, :cond_2d

    .line 288
    :try_start_2a
    invoke-virtual {v1}, Ljava/io/InputStream;->close()V
    :try_end_2d
    .catch Ljava/io/IOException; {:try_start_2a .. :try_end_2d} :catch_2e

    .line 291
    :cond_2d
    :goto_2d
    throw v4

    .line 289
    :catch_2e
    move-exception v0

    .line 290
    .restart local v0    # "e":Ljava/io/IOException;
    invoke-virtual {v0}, Ljava/io/IOException;->printStackTrace()V

    goto :goto_2d

    .line 286
    .end local v0    # "e":Ljava/io/IOException;
    .end local v1    # "in":Ljava/io/InputStream;
    .restart local v2    # "in":Ljava/io/InputStream;
    :catchall_33
    move-exception v4

    move-object v1, v2

    .end local v2    # "in":Ljava/io/InputStream;
    .restart local v1    # "in":Ljava/io/InputStream;
    goto :goto_28

    .line 284
    .end local v1    # "in":Ljava/io/InputStream;
    .restart local v2    # "in":Ljava/io/InputStream;
    :catch_36
    move-exception v4

    move-object v1, v2

    .end local v2    # "in":Ljava/io/InputStream;
    .restart local v1    # "in":Ljava/io/InputStream;
    goto :goto_1c

    .end local v1    # "in":Ljava/io/InputStream;
    .restart local v2    # "in":Ljava/io/InputStream;
    :cond_39
    move-object v1, v2

    .end local v2    # "in":Ljava/io/InputStream;
    .restart local v1    # "in":Ljava/io/InputStream;
    goto :goto_14
.end method

.method public static getStringValue(Ljava/lang/String;)Ljava/lang/String;
    .registers 8
    .param p0, "key"    # Ljava/lang/String;

    .prologue
    .line 134
    invoke-static {}, Landroid/bb_sz/os/SystemProperties;->init()V

    .line 135
    const/4 v2, 0x0

    .line 136
    .local v2, "result":Ljava/lang/String;
    invoke-static {p0}, Landroid/bb_sz/os/SystemProperties;->changed(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    .line 137
    .local v1, "newKey":Ljava/lang/String;
    new-instance v3, Ljava/io/File;

    const-string v4, "/data/local/tmp/test.prop"

    invoke-direct {v3, v4}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-virtual {v3}, Ljava/io/File;->exists()Z

    move-result v3

    if-eqz v3, :cond_2d

    invoke-static {v1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v3

    if-nez v3, :cond_2d

    const-string v3, "refresh"

    invoke-virtual {v1, v3}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v3

    if-eqz v3, :cond_2d

    .line 138
    invoke-static {}, Landroid/bb_sz/os/SystemProperties;->getMockProp()Ljava/util/Properties;

    move-result-object v3

    const-string v4, ""

    invoke-virtual {v3, v1, v4}, Ljava/util/Properties;->getProperty(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    .line 141
    :cond_2d
    if-nez v2, :cond_42

    sget-object v3, Landroid/bb_sz/os/SystemProperties;->mNativeGetString_1:Ljava/lang/reflect/Method;

    if-eqz v3, :cond_42

    .line 143
    :try_start_33
    sget-object v3, Landroid/bb_sz/os/SystemProperties;->mNativeGetString_1:Ljava/lang/reflect/Method;

    const/4 v4, 0x0

    const/4 v5, 0x1

    new-array v5, v5, [Ljava/lang/Object;

    const/4 v6, 0x0

    aput-object p0, v5, v6

    invoke-virtual {v3, v4, v5}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v2

    .end local v2    # "result":Ljava/lang/String;
    check-cast v2, Ljava/lang/String;
    :try_end_42
    .catch Ljava/lang/Exception; {:try_start_33 .. :try_end_42} :catch_69

    .line 152
    .restart local v2    # "result":Ljava/lang/String;
    :cond_42
    :goto_42
    sget-boolean v3, Landroid/bb_sz/os/SystemProperties;->debug:Z

    if-eqz v3, :cond_68

    const-string v3, "sky2"

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "getStringValue0 key = "

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    const-string v5, ", value = "

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-static {v3, v4}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    .line 153
    :cond_68
    return-object v2

    .line 144
    .end local v2    # "result":Ljava/lang/String;
    :catch_69
    move-exception v0

    .line 145
    .local v0, "e":Ljava/lang/Exception;
    invoke-virtual {v0}, Ljava/lang/Exception;->printStackTrace()V

    .line 146
    const-string v2, ""

    .line 147
    .restart local v2    # "result":Ljava/lang/String;
    sget-boolean v3, Landroid/bb_sz/os/SystemProperties;->debug:Z

    if-eqz v3, :cond_42

    .line 148
    const-string v3, "sky2"

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "getStringValue0 err key = "

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    const-string v5, ", value = "

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-static {v3, v4}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_42
.end method

.method public static getStringValue(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    .registers 9
    .param p0, "key"    # Ljava/lang/String;
    .param p1, "value"    # Ljava/lang/String;

    .prologue
    .line 157
    invoke-static {}, Landroid/bb_sz/os/SystemProperties;->init()V

    .line 158
    move-object v2, p1

    .line 159
    .local v2, "result":Ljava/lang/String;
    invoke-static {p0}, Landroid/bb_sz/os/SystemProperties;->changed(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    .line 160
    .local v1, "newKey":Ljava/lang/String;
    new-instance v3, Ljava/io/File;

    const-string v4, "/data/local/tmp/test.prop"

    invoke-direct {v3, v4}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-virtual {v3}, Ljava/io/File;->exists()Z

    move-result v3

    if-eqz v3, :cond_3e

    invoke-static {v1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v3

    if-nez v3, :cond_3e

    const-string v3, "refresh"

    invoke-virtual {v1, v3}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v3

    if-eqz v3, :cond_3e

    .line 161
    invoke-static {}, Landroid/bb_sz/os/SystemProperties;->getMockProp()Ljava/util/Properties;

    move-result-object v3

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, ""

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v3, v1, v4}, Ljava/util/Properties;->getProperty(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    .line 164
    :cond_3e
    if-eqz v2, :cond_5c

    invoke-virtual {v2, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_5c

    sget-object v3, Landroid/bb_sz/os/SystemProperties;->mNativeGetString_2:Ljava/lang/reflect/Method;

    if-eqz v3, :cond_5c

    .line 166
    :try_start_4a
    sget-object v3, Landroid/bb_sz/os/SystemProperties;->mNativeGetString_2:Ljava/lang/reflect/Method;

    const/4 v4, 0x0

    const/4 v5, 0x2

    new-array v5, v5, [Ljava/lang/Object;

    const/4 v6, 0x0

    aput-object p0, v5, v6

    const/4 v6, 0x1

    aput-object p1, v5, v6

    invoke-virtual {v3, v4, v5}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v2

    .end local v2    # "result":Ljava/lang/String;
    check-cast v2, Ljava/lang/String;
    :try_end_5c
    .catch Ljava/lang/Exception; {:try_start_4a .. :try_end_5c} :catch_83

    .line 175
    .restart local v2    # "result":Ljava/lang/String;
    :cond_5c
    :goto_5c
    sget-boolean v3, Landroid/bb_sz/os/SystemProperties;->debug:Z

    if-eqz v3, :cond_82

    const-string v3, "sky2"

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "getStringValue key = "

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    const-string v5, ", value = "

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-static {v3, v4}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    .line 176
    :cond_82
    return-object v2

    .line 167
    .end local v2    # "result":Ljava/lang/String;
    :catch_83
    move-exception v0

    .line 168
    .local v0, "e":Ljava/lang/Exception;
    invoke-virtual {v0}, Ljava/lang/Exception;->printStackTrace()V

    .line 169
    move-object v2, p1

    .line 170
    .restart local v2    # "result":Ljava/lang/String;
    sget-boolean v3, Landroid/bb_sz/os/SystemProperties;->debug:Z

    if-eqz v3, :cond_5c

    .line 171
    const-string v3, "sky2"

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "getStringValue err key = "

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    const-string v5, ", value = "

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    const-string v5, ", msg = "

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-static {v3, v4}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_5c
.end method

.method private static init()V
    .registers 7

    .prologue
    .line 71
    :try_start_0
    sget-object v2, Landroid/bb_sz/os/SystemProperties;->mCls:Ljava/lang/Class;

    if-nez v2, :cond_c

    .line 72
    const-string v2, "android.os.SystemProperties"

    invoke-static {v2}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v2

    sput-object v2, Landroid/bb_sz/os/SystemProperties;->mCls:Ljava/lang/Class;
    :try_end_c
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_c} :catch_74

    .line 78
    .local v1, "ignored":Ljava/lang/Exception;
    :cond_c
    :goto_c
    sget-object v2, Landroid/bb_sz/os/SystemProperties;->mCls:Ljava/lang/Class;

    if-eqz v2, :cond_22

    .line 80
    :try_start_10
    sget-object v2, Landroid/bb_sz/os/SystemProperties;->mCls:Ljava/lang/Class;

    const-string v3, "_get"

    const/4 v4, 0x1

    new-array v4, v4, [Ljava/lang/Class;

    const/4 v5, 0x0

    const-class v6, Ljava/lang/String;

    aput-object v6, v4, v5

    invoke-virtual {v2, v3, v4}, Ljava/lang/Class;->getMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object v2

    sput-object v2, Landroid/bb_sz/os/SystemProperties;->mNativeGetString_1:Ljava/lang/reflect/Method;
    :try_end_22
    .catch Ljava/lang/NoSuchMethodException; {:try_start_10 .. :try_end_22} :catch_79

    .line 85
    :cond_22
    :goto_22
    sget-object v2, Landroid/bb_sz/os/SystemProperties;->mCls:Ljava/lang/Class;

    .end local v1    # "ignored":Ljava/lang/Exception;
    if-eqz v2, :cond_3d

    .line 87
    :try_start_26
    sget-object v2, Landroid/bb_sz/os/SystemProperties;->mCls:Ljava/lang/Class;

    const-string v3, "_get"

    const/4 v4, 0x2

    new-array v4, v4, [Ljava/lang/Class;

    const/4 v5, 0x0

    const-class v6, Ljava/lang/String;

    aput-object v6, v4, v5

    const/4 v5, 0x1

    const-class v6, Ljava/lang/String;

    aput-object v6, v4, v5

    invoke-virtual {v2, v3, v4}, Ljava/lang/Class;->getMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object v2

    sput-object v2, Landroid/bb_sz/os/SystemProperties;->mNativeGetString_2:Ljava/lang/reflect/Method;
    :try_end_3d
    .catch Ljava/lang/NoSuchMethodException; {:try_start_26 .. :try_end_3d} :catch_7e

    .line 92
    :cond_3d
    :goto_3d
    sget-object v2, Landroid/bb_sz/os/SystemProperties;->mCls:Ljava/lang/Class;

    if-eqz v2, :cond_58

    .line 94
    :try_start_41
    sget-object v2, Landroid/bb_sz/os/SystemProperties;->mCls:Ljava/lang/Class;

    const-string v3, "_getInt"

    const/4 v4, 0x2

    new-array v4, v4, [Ljava/lang/Class;

    const/4 v5, 0x0

    const-class v6, Ljava/lang/String;

    aput-object v6, v4, v5

    const/4 v5, 0x1

    sget-object v6, Ljava/lang/Integer;->TYPE:Ljava/lang/Class;

    aput-object v6, v4, v5

    invoke-virtual {v2, v3, v4}, Ljava/lang/Class;->getMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object v2

    sput-object v2, Landroid/bb_sz/os/SystemProperties;->mNativeGetInt:Ljava/lang/reflect/Method;
    :try_end_58
    .catch Ljava/lang/NoSuchMethodException; {:try_start_41 .. :try_end_58} :catch_83

    .line 99
    :cond_58
    :goto_58
    sget-object v2, Landroid/bb_sz/os/SystemProperties;->mCls:Ljava/lang/Class;

    if-eqz v2, :cond_73

    .line 101
    :try_start_5c
    sget-object v2, Landroid/bb_sz/os/SystemProperties;->mCls:Ljava/lang/Class;

    const-string v3, "_getLong"

    const/4 v4, 0x2

    new-array v4, v4, [Ljava/lang/Class;

    const/4 v5, 0x0

    const-class v6, Ljava/lang/String;

    aput-object v6, v4, v5

    const/4 v5, 0x1

    sget-object v6, Ljava/lang/Long;->TYPE:Ljava/lang/Class;

    aput-object v6, v4, v5

    invoke-virtual {v2, v3, v4}, Ljava/lang/Class;->getMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object v2

    sput-object v2, Landroid/bb_sz/os/SystemProperties;->mNativeGetLong:Ljava/lang/reflect/Method;
    :try_end_73
    .catch Ljava/lang/NoSuchMethodException; {:try_start_5c .. :try_end_73} :catch_88

    .line 106
    :cond_73
    :goto_73
    return-void

    .line 74
    :catch_74
    move-exception v1

    .line 75
    .restart local v1    # "ignored":Ljava/lang/Exception;
    const/4 v2, 0x0

    sput-object v2, Landroid/bb_sz/os/SystemProperties;->mCls:Ljava/lang/Class;

    goto :goto_c

    .line 81
    :catch_79
    move-exception v0

    .line 82
    .local v0, "e":Ljava/lang/NoSuchMethodException;
    invoke-virtual {v0}, Ljava/lang/NoSuchMethodException;->printStackTrace()V

    goto :goto_22

    .line 88
    .end local v0    # "e":Ljava/lang/NoSuchMethodException;
    .end local v1    # "ignored":Ljava/lang/Exception;
    :catch_7e
    move-exception v0

    .line 89
    .restart local v0    # "e":Ljava/lang/NoSuchMethodException;
    invoke-virtual {v0}, Ljava/lang/NoSuchMethodException;->printStackTrace()V

    goto :goto_3d

    .line 95
    .end local v0    # "e":Ljava/lang/NoSuchMethodException;
    :catch_83
    move-exception v0

    .line 96
    .restart local v0    # "e":Ljava/lang/NoSuchMethodException;
    invoke-virtual {v0}, Ljava/lang/NoSuchMethodException;->printStackTrace()V

    goto :goto_58

    .line 102
    .end local v0    # "e":Ljava/lang/NoSuchMethodException;
    :catch_88
    move-exception v0

    .line 103
    .restart local v0    # "e":Ljava/lang/NoSuchMethodException;
    invoke-virtual {v0}, Ljava/lang/NoSuchMethodException;->printStackTrace()V

    goto :goto_73
.end method

.method public static isNeedChange()Z
    .registers 1

    .prologue
    .line 31
    const/4 v0, 0x1

    return v0
.end method

.method public static native_get(Ljava/lang/String;)Ljava/lang/String;
    .registers 4
    .param p0, "key"    # Ljava/lang/String;

    .prologue
    .line 114
    sget-boolean v0, Landroid/bb_sz/os/SystemProperties;->debug:Z

    if-eqz v0, :cond_1c

    const-string v0, "sky"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "native_get key = "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    .line 115
    :cond_1c
    invoke-static {p0}, Landroid/bb_sz/os/SystemProperties;->getStringValue(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public static native_get(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    .registers 5
    .param p0, "key"    # Ljava/lang/String;
    .param p1, "value"    # Ljava/lang/String;

    .prologue
    .line 119
    sget-boolean v0, Landroid/bb_sz/os/SystemProperties;->debug:Z

    if-eqz v0, :cond_26

    const-string v0, "sky"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "native_get key = "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, ", value = "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    .line 120
    :cond_26
    invoke-static {p0, p1}, Landroid/bb_sz/os/SystemProperties;->getStringValue(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public static native_get_int(Ljava/lang/String;I)I
    .registers 5
    .param p0, "key"    # Ljava/lang/String;
    .param p1, "value"    # I

    .prologue
    .line 124
    sget-boolean v0, Landroid/bb_sz/os/SystemProperties;->debug:Z

    if-eqz v0, :cond_26

    const-string v0, "sky"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "native_get_int key = "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, ", value = "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    .line 125
    :cond_26
    invoke-static {p0, p1}, Landroid/bb_sz/os/SystemProperties;->getIntValue(Ljava/lang/String;I)I

    move-result v0

    return v0
.end method

.method public static native_get_long(Ljava/lang/String;J)J
    .registers 6
    .param p0, "key"    # Ljava/lang/String;
    .param p1, "value"    # J

    .prologue
    .line 129
    sget-boolean v0, Landroid/bb_sz/os/SystemProperties;->debug:Z

    if-eqz v0, :cond_26

    const-string v0, "sky"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "native_get_long key = "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, ", value = "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p1, p2}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    .line 130
    :cond_26
    invoke-static {p0, p1, p2}, Landroid/bb_sz/os/SystemProperties;->getLongValue(Ljava/lang/String;J)J

    move-result-wide v0

    return-wide v0
.end method
