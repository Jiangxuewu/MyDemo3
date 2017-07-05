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

.field private static final propFile:Ljava/lang/String; = "/data/local/tmp/test.prop"

.field private static final resetPropFile:Ljava/lang/String; = "/data/local/tmp/reset.prop"


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
    const/4 v0, 0x0

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
    .line 206
    const-string v0, "ro.build.id"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_b

    .line 207
    const-string p0, "refresh.id"

    .line 235
    .end local p0    # "key":Ljava/lang/String;
    :cond_a
    :goto_a
    return-object p0

    .line 208
    .restart local p0    # "key":Ljava/lang/String;
    :cond_b
    const-string v0, "ro.product.model"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_16

    .line 209
    const-string p0, "refresh.model"

    goto :goto_a

    .line 210
    :cond_16
    const-string v0, "ro.serialno"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_21

    .line 211
    const-string p0, "refresh.serial"

    goto :goto_a

    .line 212
    :cond_21
    const-string v0, "ro.build.version.release"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_2c

    .line 213
    const-string p0, "refresh.version"

    goto :goto_a

    .line 214
    :cond_2c
    const-string v0, "ro.build.version.sdk"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_37

    .line 215
    const-string p0, "refresh.api"

    goto :goto_a

    .line 216
    :cond_37
    const-string v0, "ro.product.manufacturer"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_42

    .line 217
    const-string p0, "refresh.manufacturer"

    goto :goto_a

    .line 218
    :cond_42
    const-string v0, "ro.product.brand"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_4d

    .line 219
    const-string p0, "refresh.brand"

    goto :goto_a

    .line 220
    :cond_4d
    const-string v0, "ro.build.product"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_5d

    const-string v0, "ro.product.name"

    invoke-virtual {v0, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_60

    .line 221
    :cond_5d
    const-string p0, "refresh.product"

    goto :goto_a

    .line 222
    :cond_60
    const-string v0, "ro.product.device"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_6b

    .line 223
    const-string p0, "refresh.device"

    goto :goto_a

    .line 224
    :cond_6b
    const-string v0, "ro.product.board"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_76

    .line 225
    const-string p0, "refresh.board"

    goto :goto_a

    .line 226
    :cond_76
    const-string v0, "ro.product.cpu.abi"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_81

    .line 227
    const-string p0, "refresh.cpuabi"

    goto :goto_a

    .line 228
    :cond_81
    const-string v0, "ro.product.cpu.abi2"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_8d

    .line 229
    const-string p0, "refresh.cpuabi2"

    goto/16 :goto_a

    .line 230
    :cond_8d
    const-string v0, "ro.hardware"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_99

    .line 231
    const-string p0, "refresh.hardware"

    goto/16 :goto_a

    .line 232
    :cond_99
    const-string v0, "qemu.sf.lcd_density"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_a9

    const-string v0, "ro.sf.lcd_density"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_a

    .line 233
    :cond_a9
    const-string p0, "refresh.densityDpi"

    goto/16 :goto_a
.end method

.method public static getFloat(Ljava/lang/String;I)F
    .registers 5
    .param p0, "key"    # Ljava/lang/String;
    .param p1, "value"    # I

    .prologue
    .line 73
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

    .line 74
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
    .line 194
    invoke-static {}, Landroid/bb_sz/os/SystemProperties;->init()V

    .line 195
    move v1, p1

    .line 196
    .local v1, "result":F
    invoke-static {p0}, Landroid/bb_sz/os/SystemProperties;->changed(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 197
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

    .line 198
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

    .line 200
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

    .line 201
    :cond_68
    return v1
.end method

.method public static getIntValue(Ljava/lang/String;I)I
    .registers 12
    .param p0, "key"    # Ljava/lang/String;
    .param p1, "value"    # I

    .prologue
    .line 165
    invoke-static {}, Landroid/bb_sz/os/SystemProperties;->init()V

    .line 166
    move v4, p1

    .line 167
    .local v4, "result":I
    invoke-static {p0}, Landroid/bb_sz/os/SystemProperties;->changed(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    .line 168
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

    .line 170
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

    .line 177
    :cond_42
    :goto_42
    if-ne v4, p1, :cond_68

    sget-object v5, Landroid/bb_sz/os/SystemProperties;->mNativeGetInt:Ljava/lang/reflect/Method;

    if-eqz v5, :cond_68

    .line 179
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

    .line 180
    .local v3, "object":Ljava/lang/Object;
    if-eqz v3, :cond_68

    instance-of v5, v3, Ljava/lang/Integer;

    if-eqz v5, :cond_68

    .line 181
    check-cast v3, Ljava/lang/Integer;

    .end local v3    # "object":Ljava/lang/Object;
    invoke-virtual {v3}, Ljava/lang/Integer;->intValue()I
    :try_end_67
    .catch Ljava/lang/Exception; {:try_start_48 .. :try_end_67} :catch_b8

    move-result v4

    .line 189
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

    .line 190
    :cond_8e
    return v4

    .line 171
    :catch_8f
    move-exception v1

    .line 172
    .local v1, "ignored":Ljava/lang/Exception;
    move v4, p1

    .line 173
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

    .line 182
    .end local v1    # "ignored":Ljava/lang/Exception;
    :catch_b8
    move-exception v0

    .line 183
    .local v0, "e":Ljava/lang/Exception;
    invoke-virtual {v0}, Ljava/lang/Exception;->printStackTrace()V

    .line 184
    move v4, p1

    .line 185
    sget-boolean v5, Landroid/bb_sz/os/SystemProperties;->debug:Z

    if-eqz v5, :cond_68

    .line 186
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
    .line 144
    invoke-static {}, Landroid/bb_sz/os/SystemProperties;->init()V

    .line 145
    move-wide v2, p1

    .line 146
    .local v2, "result":J
    invoke-static {p0}, Landroid/bb_sz/os/SystemProperties;->changed(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    .line 147
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

    .line 148
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

    .line 151
    :cond_42
    cmp-long v4, v2, p1

    if-nez v4, :cond_64

    sget-object v4, Landroid/bb_sz/os/SystemProperties;->mNativeGetLong:Ljava/lang/reflect/Method;

    if-eqz v4, :cond_64

    .line 153
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

    .line 160
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

    .line 161
    :cond_8a
    return-wide v2

    .line 154
    :catch_8b
    move-exception v0

    .line 155
    .local v0, "e":Ljava/lang/Exception;
    invoke-virtual {v0}, Ljava/lang/Exception;->printStackTrace()V

    .line 156
    move-wide v2, p1

    .line 157
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
    .registers 5

    .prologue
    .line 239
    invoke-static {}, Landroid/os/SystemClock;->elapsedRealtime()J

    move-result-wide v0

    .line 240
    .local v0, "time":J
    const-string v2, "SKYTime"

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "elapsedRealtime = "

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3, v0, v1}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-static {v2, v3}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    .line 241
    const-wide/32 v2, 0xea60

    cmp-long v2, v0, v2

    if-gtz v2, :cond_2a

    .line 242
    const-string v2, "/data/local/tmp/reset.prop"

    invoke-static {v2}, Landroid/bb_sz/os/SystemProperties;->getMockProp(Ljava/lang/String;)Ljava/util/Properties;

    move-result-object v2

    .line 244
    :goto_29
    return-object v2

    :cond_2a
    const-string v2, "/data/local/tmp/test.prop"

    invoke-static {v2}, Landroid/bb_sz/os/SystemProperties;->getMockProp(Ljava/lang/String;)Ljava/util/Properties;

    move-result-object v2

    goto :goto_29
.end method

.method public static getMockProp(Ljava/lang/String;)Ljava/util/Properties;
    .registers 6
    .param p0, "file"    # Ljava/lang/String;

    .prologue
    .line 248
    new-instance v3, Ljava/util/Properties;

    invoke-direct {v3}, Ljava/util/Properties;-><init>()V

    .line 249
    .local v3, "prop":Ljava/util/Properties;
    const/4 v1, 0x0

    .line 251
    .local v1, "in":Ljava/io/InputStream;
    :try_start_6
    new-instance v2, Ljava/io/FileInputStream;

    invoke-direct {v2, p0}, Ljava/io/FileInputStream;-><init>(Ljava/lang/String;)V
    :try_end_b
    .catch Ljava/lang/Exception; {:try_start_6 .. :try_end_b} :catch_1b
    .catchall {:try_start_6 .. :try_end_b} :catchall_27

    .line 252
    .end local v1    # "in":Ljava/io/InputStream;
    .local v2, "in":Ljava/io/InputStream;
    :try_start_b
    invoke-virtual {v3, v2}, Ljava/util/Properties;->load(Ljava/io/InputStream;)V
    :try_end_e
    .catch Ljava/lang/Exception; {:try_start_b .. :try_end_e} :catch_36
    .catchall {:try_start_b .. :try_end_e} :catchall_33

    .line 255
    if-eqz v2, :cond_39

    .line 257
    :try_start_10
    invoke-virtual {v2}, Ljava/io/InputStream;->close()V
    :try_end_13
    .catch Ljava/io/IOException; {:try_start_10 .. :try_end_13} :catch_15

    move-object v1, v2

    .line 262
    .end local v2    # "in":Ljava/io/InputStream;
    .restart local v1    # "in":Ljava/io/InputStream;
    :cond_14
    :goto_14
    return-object v3

    .line 258
    .end local v1    # "in":Ljava/io/InputStream;
    .restart local v2    # "in":Ljava/io/InputStream;
    :catch_15
    move-exception v0

    .line 259
    .local v0, "e":Ljava/io/IOException;
    invoke-virtual {v0}, Ljava/io/IOException;->printStackTrace()V

    move-object v1, v2

    .line 260
    .end local v2    # "in":Ljava/io/InputStream;
    .restart local v1    # "in":Ljava/io/InputStream;
    goto :goto_14

    .line 253
    .end local v0    # "e":Ljava/io/IOException;
    :catch_1b
    move-exception v4

    .line 255
    :goto_1c
    if-eqz v1, :cond_14

    .line 257
    :try_start_1e
    invoke-virtual {v1}, Ljava/io/InputStream;->close()V
    :try_end_21
    .catch Ljava/io/IOException; {:try_start_1e .. :try_end_21} :catch_22

    goto :goto_14

    .line 258
    :catch_22
    move-exception v0

    .line 259
    .restart local v0    # "e":Ljava/io/IOException;
    invoke-virtual {v0}, Ljava/io/IOException;->printStackTrace()V

    goto :goto_14

    .line 255
    .end local v0    # "e":Ljava/io/IOException;
    :catchall_27
    move-exception v4

    :goto_28
    if-eqz v1, :cond_2d

    .line 257
    :try_start_2a
    invoke-virtual {v1}, Ljava/io/InputStream;->close()V
    :try_end_2d
    .catch Ljava/io/IOException; {:try_start_2a .. :try_end_2d} :catch_2e

    .line 260
    :cond_2d
    :goto_2d
    throw v4

    .line 258
    :catch_2e
    move-exception v0

    .line 259
    .restart local v0    # "e":Ljava/io/IOException;
    invoke-virtual {v0}, Ljava/io/IOException;->printStackTrace()V

    goto :goto_2d

    .line 255
    .end local v0    # "e":Ljava/io/IOException;
    .end local v1    # "in":Ljava/io/InputStream;
    .restart local v2    # "in":Ljava/io/InputStream;
    :catchall_33
    move-exception v4

    move-object v1, v2

    .end local v2    # "in":Ljava/io/InputStream;
    .restart local v1    # "in":Ljava/io/InputStream;
    goto :goto_28

    .line 253
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
    .line 98
    invoke-static {}, Landroid/bb_sz/os/SystemProperties;->init()V

    .line 99
    const/4 v2, 0x0

    .line 100
    .local v2, "result":Ljava/lang/String;
    invoke-static {p0}, Landroid/bb_sz/os/SystemProperties;->changed(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    .line 101
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

    .line 102
    invoke-static {}, Landroid/bb_sz/os/SystemProperties;->getMockProp()Ljava/util/Properties;

    move-result-object v3

    const-string v4, ""

    invoke-virtual {v3, v1, v4}, Ljava/util/Properties;->getProperty(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    .line 105
    :cond_2d
    if-nez v2, :cond_42

    sget-object v3, Landroid/bb_sz/os/SystemProperties;->mNativeGetString_1:Ljava/lang/reflect/Method;

    if-eqz v3, :cond_42

    .line 107
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

    .line 116
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

    .line 117
    :cond_68
    return-object v2

    .line 108
    .end local v2    # "result":Ljava/lang/String;
    :catch_69
    move-exception v0

    .line 109
    .local v0, "e":Ljava/lang/Exception;
    invoke-virtual {v0}, Ljava/lang/Exception;->printStackTrace()V

    .line 110
    const-string v2, ""

    .line 111
    .restart local v2    # "result":Ljava/lang/String;
    sget-boolean v3, Landroid/bb_sz/os/SystemProperties;->debug:Z

    if-eqz v3, :cond_42

    .line 112
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
    .line 121
    invoke-static {}, Landroid/bb_sz/os/SystemProperties;->init()V

    .line 122
    move-object v2, p1

    .line 123
    .local v2, "result":Ljava/lang/String;
    invoke-static {p0}, Landroid/bb_sz/os/SystemProperties;->changed(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    .line 124
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

    .line 125
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

    .line 128
    :cond_3e
    if-eqz v2, :cond_5c

    invoke-virtual {v2, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_5c

    sget-object v3, Landroid/bb_sz/os/SystemProperties;->mNativeGetString_2:Ljava/lang/reflect/Method;

    if-eqz v3, :cond_5c

    .line 130
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

    .line 139
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

    .line 140
    :cond_82
    return-object v2

    .line 131
    .end local v2    # "result":Ljava/lang/String;
    :catch_83
    move-exception v0

    .line 132
    .local v0, "e":Ljava/lang/Exception;
    invoke-virtual {v0}, Ljava/lang/Exception;->printStackTrace()V

    .line 133
    move-object v2, p1

    .line 134
    .restart local v2    # "result":Ljava/lang/String;
    sget-boolean v3, Landroid/bb_sz/os/SystemProperties;->debug:Z

    if-eqz v3, :cond_5c

    .line 135
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
    .line 35
    :try_start_0
    sget-object v2, Landroid/bb_sz/os/SystemProperties;->mCls:Ljava/lang/Class;

    if-nez v2, :cond_c

    .line 36
    const-string v2, "android.os.SystemProperties"

    invoke-static {v2}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v2

    sput-object v2, Landroid/bb_sz/os/SystemProperties;->mCls:Ljava/lang/Class;
    :try_end_c
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_c} :catch_74

    .line 42
    .local v1, "ignored":Ljava/lang/Exception;
    :cond_c
    :goto_c
    sget-object v2, Landroid/bb_sz/os/SystemProperties;->mCls:Ljava/lang/Class;

    if-eqz v2, :cond_22

    .line 44
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

    .line 49
    :cond_22
    :goto_22
    sget-object v2, Landroid/bb_sz/os/SystemProperties;->mCls:Ljava/lang/Class;

    .end local v1    # "ignored":Ljava/lang/Exception;
    if-eqz v2, :cond_3d

    .line 51
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

    .line 56
    :cond_3d
    :goto_3d
    sget-object v2, Landroid/bb_sz/os/SystemProperties;->mCls:Ljava/lang/Class;

    if-eqz v2, :cond_58

    .line 58
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

    .line 63
    :cond_58
    :goto_58
    sget-object v2, Landroid/bb_sz/os/SystemProperties;->mCls:Ljava/lang/Class;

    if-eqz v2, :cond_73

    .line 65
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

    .line 70
    :cond_73
    :goto_73
    return-void

    .line 38
    :catch_74
    move-exception v1

    .line 39
    .restart local v1    # "ignored":Ljava/lang/Exception;
    const/4 v2, 0x0

    sput-object v2, Landroid/bb_sz/os/SystemProperties;->mCls:Ljava/lang/Class;

    goto :goto_c

    .line 45
    :catch_79
    move-exception v0

    .line 46
    .local v0, "e":Ljava/lang/NoSuchMethodException;
    invoke-virtual {v0}, Ljava/lang/NoSuchMethodException;->printStackTrace()V

    goto :goto_22

    .line 52
    .end local v0    # "e":Ljava/lang/NoSuchMethodException;
    .end local v1    # "ignored":Ljava/lang/Exception;
    :catch_7e
    move-exception v0

    .line 53
    .restart local v0    # "e":Ljava/lang/NoSuchMethodException;
    invoke-virtual {v0}, Ljava/lang/NoSuchMethodException;->printStackTrace()V

    goto :goto_3d

    .line 59
    .end local v0    # "e":Ljava/lang/NoSuchMethodException;
    :catch_83
    move-exception v0

    .line 60
    .restart local v0    # "e":Ljava/lang/NoSuchMethodException;
    invoke-virtual {v0}, Ljava/lang/NoSuchMethodException;->printStackTrace()V

    goto :goto_58

    .line 66
    .end local v0    # "e":Ljava/lang/NoSuchMethodException;
    :catch_88
    move-exception v0

    .line 67
    .restart local v0    # "e":Ljava/lang/NoSuchMethodException;
    invoke-virtual {v0}, Ljava/lang/NoSuchMethodException;->printStackTrace()V

    goto :goto_73
.end method

.method public static isNeedChange()Z
    .registers 1

    .prologue
    .line 30
    const/4 v0, 0x1

    return v0
.end method

.method public static native_get(Ljava/lang/String;)Ljava/lang/String;
    .registers 4
    .param p0, "key"    # Ljava/lang/String;

    .prologue
    .line 78
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

    .line 79
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
    .line 83
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

    .line 84
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
    .line 88
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

    .line 89
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
    .line 93
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

    .line 94
    :cond_26
    invoke-static {p0, p1, p2}, Landroid/bb_sz/os/SystemProperties;->getLongValue(Ljava/lang/String;J)J

    move-result-wide v0

    return-wide v0
.end method
