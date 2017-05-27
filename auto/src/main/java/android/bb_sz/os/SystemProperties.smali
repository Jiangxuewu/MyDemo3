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


# direct methods
.method static constructor <clinit>()V
    .registers 1

    .prologue
    const/4 v0, 0x0

    .line 18
    sput-object v0, Landroid/bb_sz/os/SystemProperties;->mCls:Ljava/lang/Class;

    .line 19
    sput-object v0, Landroid/bb_sz/os/SystemProperties;->mNativeGetInt:Ljava/lang/reflect/Method;

    .line 20
    sput-object v0, Landroid/bb_sz/os/SystemProperties;->mNativeGetLong:Ljava/lang/reflect/Method;

    .line 21
    sput-object v0, Landroid/bb_sz/os/SystemProperties;->mNativeGetString_1:Ljava/lang/reflect/Method;

    .line 22
    sput-object v0, Landroid/bb_sz/os/SystemProperties;->mNativeGetString_2:Ljava/lang/reflect/Method;

    .line 25
    const/4 v0, 0x1

    sput-boolean v0, Landroid/bb_sz/os/SystemProperties;->debug:Z

    return-void
.end method

.method public constructor <init>()V
    .registers 1

    .prologue
    .line 17
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method private static changed(Ljava/lang/String;)Ljava/lang/String;
    .registers 2
    .param p0, "key"    # Ljava/lang/String;

    .prologue
    .line 200
    const-string v0, "ro.build.id"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_b

    .line 201
    const-string p0, "refresh.id"

    .line 229
    .end local p0    # "key":Ljava/lang/String;
    :cond_a
    :goto_a
    return-object p0

    .line 202
    .restart local p0    # "key":Ljava/lang/String;
    :cond_b
    const-string v0, "ro.product.model"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_16

    .line 203
    const-string p0, "refresh.model"

    goto :goto_a

    .line 204
    :cond_16
    const-string v0, "ro.serialno"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_21

    .line 205
    const-string p0, "refresh.serial"

    goto :goto_a

    .line 206
    :cond_21
    const-string v0, "ro.build.version.release"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_2c

    .line 207
    const-string p0, "refresh.version"

    goto :goto_a

    .line 208
    :cond_2c
    const-string v0, "ro.build.version.sdk"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_37

    .line 209
    const-string p0, "refresh.api"

    goto :goto_a

    .line 210
    :cond_37
    const-string v0, "ro.product.manufacturer"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_42

    .line 211
    const-string p0, "refresh.manufacturer"

    goto :goto_a

    .line 212
    :cond_42
    const-string v0, "ro.product.brand"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_4d

    .line 213
    const-string p0, "refresh.brand"

    goto :goto_a

    .line 214
    :cond_4d
    const-string v0, "ro.build.product"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_5d

    const-string v0, "ro.product.name"

    invoke-virtual {v0, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_60

    .line 215
    :cond_5d
    const-string p0, "refresh.product"

    goto :goto_a

    .line 216
    :cond_60
    const-string v0, "ro.product.device"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_6b

    .line 217
    const-string p0, "refresh.device"

    goto :goto_a

    .line 218
    :cond_6b
    const-string v0, "ro.product.board"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_76

    .line 219
    const-string p0, "refresh.board"

    goto :goto_a

    .line 220
    :cond_76
    const-string v0, "ro.product.cpu.abi"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_81

    .line 221
    const-string p0, "refresh.cpuabi"

    goto :goto_a

    .line 222
    :cond_81
    const-string v0, "ro.product.cpu.abi2"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_8d

    .line 223
    const-string p0, "refresh.cpuabi2"

    goto/16 :goto_a

    .line 224
    :cond_8d
    const-string v0, "ro.hardware"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_99

    .line 225
    const-string p0, "refresh.hardware"

    goto/16 :goto_a

    .line 226
    :cond_99
    const-string v0, "qemu.sf.lcd_density"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_a9

    const-string v0, "ro.sf.lcd_density"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_a

    .line 227
    :cond_a9
    const-string p0, "refresh.densityDpi"

    goto/16 :goto_a
.end method

.method public static getFloat(Ljava/lang/String;I)F
    .registers 5
    .param p0, "key"    # Ljava/lang/String;
    .param p1, "value"    # I

    .prologue
    .line 67
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

    .line 68
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
    .line 188
    invoke-static {}, Landroid/bb_sz/os/SystemProperties;->init()V

    .line 189
    move v1, p1

    .line 190
    .local v1, "result":F
    invoke-static {p0}, Landroid/bb_sz/os/SystemProperties;->changed(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 191
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

    .line 192
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

    .line 194
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

    .line 195
    :cond_68
    return v1
.end method

.method public static getIntValue(Ljava/lang/String;I)I
    .registers 12
    .param p0, "key"    # Ljava/lang/String;
    .param p1, "value"    # I

    .prologue
    .line 159
    invoke-static {}, Landroid/bb_sz/os/SystemProperties;->init()V

    .line 160
    move v4, p1

    .line 161
    .local v4, "result":I
    invoke-static {p0}, Landroid/bb_sz/os/SystemProperties;->changed(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    .line 162
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

    .line 164
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

    .line 171
    :cond_42
    :goto_42
    if-ne v4, p1, :cond_68

    sget-object v5, Landroid/bb_sz/os/SystemProperties;->mNativeGetInt:Ljava/lang/reflect/Method;

    if-eqz v5, :cond_68

    .line 173
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

    .line 174
    .local v3, "object":Ljava/lang/Object;
    if-eqz v3, :cond_68

    instance-of v5, v3, Ljava/lang/Integer;

    if-eqz v5, :cond_68

    .line 175
    check-cast v3, Ljava/lang/Integer;

    .end local v3    # "object":Ljava/lang/Object;
    invoke-virtual {v3}, Ljava/lang/Integer;->intValue()I
    :try_end_67
    .catch Ljava/lang/Exception; {:try_start_48 .. :try_end_67} :catch_b8

    move-result v4

    .line 183
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

    .line 184
    :cond_8e
    return v4

    .line 165
    :catch_8f
    move-exception v1

    .line 166
    .local v1, "ignored":Ljava/lang/Exception;
    move v4, p1

    .line 167
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

    .line 176
    .end local v1    # "ignored":Ljava/lang/Exception;
    :catch_b8
    move-exception v0

    .line 177
    .local v0, "e":Ljava/lang/Exception;
    invoke-virtual {v0}, Ljava/lang/Exception;->printStackTrace()V

    .line 178
    move v4, p1

    .line 179
    sget-boolean v5, Landroid/bb_sz/os/SystemProperties;->debug:Z

    if-eqz v5, :cond_68

    .line 180
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
    .line 138
    invoke-static {}, Landroid/bb_sz/os/SystemProperties;->init()V

    .line 139
    move-wide v2, p1

    .line 140
    .local v2, "result":J
    invoke-static {p0}, Landroid/bb_sz/os/SystemProperties;->changed(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    .line 141
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

    .line 142
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

    .line 145
    :cond_42
    cmp-long v4, v2, p1

    if-nez v4, :cond_64

    sget-object v4, Landroid/bb_sz/os/SystemProperties;->mNativeGetLong:Ljava/lang/reflect/Method;

    if-eqz v4, :cond_64

    .line 147
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

    .line 154
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

    .line 155
    :cond_8a
    return-wide v2

    .line 148
    :catch_8b
    move-exception v0

    .line 149
    .local v0, "e":Ljava/lang/Exception;
    invoke-virtual {v0}, Ljava/lang/Exception;->printStackTrace()V

    .line 150
    move-wide v2, p1

    .line 151
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
    .line 233
    new-instance v3, Ljava/util/Properties;

    invoke-direct {v3}, Ljava/util/Properties;-><init>()V

    .line 234
    .local v3, "prop":Ljava/util/Properties;
    const/4 v1, 0x0

    .line 236
    .local v1, "in":Ljava/io/InputStream;
    :try_start_6
    new-instance v2, Ljava/io/FileInputStream;

    const-string v4, "/data/local/tmp/test.prop"

    invoke-direct {v2, v4}, Ljava/io/FileInputStream;-><init>(Ljava/lang/String;)V
    :try_end_d
    .catch Ljava/lang/Exception; {:try_start_6 .. :try_end_d} :catch_1d
    .catchall {:try_start_6 .. :try_end_d} :catchall_29

    .line 237
    .end local v1    # "in":Ljava/io/InputStream;
    .local v2, "in":Ljava/io/InputStream;
    :try_start_d
    invoke-virtual {v3, v2}, Ljava/util/Properties;->load(Ljava/io/InputStream;)V
    :try_end_10
    .catch Ljava/lang/Exception; {:try_start_d .. :try_end_10} :catch_38
    .catchall {:try_start_d .. :try_end_10} :catchall_35

    .line 240
    if-eqz v2, :cond_3b

    .line 242
    :try_start_12
    invoke-virtual {v2}, Ljava/io/InputStream;->close()V
    :try_end_15
    .catch Ljava/io/IOException; {:try_start_12 .. :try_end_15} :catch_17

    move-object v1, v2

    .line 247
    .end local v2    # "in":Ljava/io/InputStream;
    .restart local v1    # "in":Ljava/io/InputStream;
    :cond_16
    :goto_16
    return-object v3

    .line 243
    .end local v1    # "in":Ljava/io/InputStream;
    .restart local v2    # "in":Ljava/io/InputStream;
    :catch_17
    move-exception v0

    .line 244
    .local v0, "e":Ljava/io/IOException;
    invoke-virtual {v0}, Ljava/io/IOException;->printStackTrace()V

    move-object v1, v2

    .line 245
    .end local v2    # "in":Ljava/io/InputStream;
    .restart local v1    # "in":Ljava/io/InputStream;
    goto :goto_16

    .line 238
    .end local v0    # "e":Ljava/io/IOException;
    :catch_1d
    move-exception v4

    .line 240
    :goto_1e
    if-eqz v1, :cond_16

    .line 242
    :try_start_20
    invoke-virtual {v1}, Ljava/io/InputStream;->close()V
    :try_end_23
    .catch Ljava/io/IOException; {:try_start_20 .. :try_end_23} :catch_24

    goto :goto_16

    .line 243
    :catch_24
    move-exception v0

    .line 244
    .restart local v0    # "e":Ljava/io/IOException;
    invoke-virtual {v0}, Ljava/io/IOException;->printStackTrace()V

    goto :goto_16

    .line 240
    .end local v0    # "e":Ljava/io/IOException;
    :catchall_29
    move-exception v4

    :goto_2a
    if-eqz v1, :cond_2f

    .line 242
    :try_start_2c
    invoke-virtual {v1}, Ljava/io/InputStream;->close()V
    :try_end_2f
    .catch Ljava/io/IOException; {:try_start_2c .. :try_end_2f} :catch_30

    .line 245
    :cond_2f
    :goto_2f
    throw v4

    .line 243
    :catch_30
    move-exception v0

    .line 244
    .restart local v0    # "e":Ljava/io/IOException;
    invoke-virtual {v0}, Ljava/io/IOException;->printStackTrace()V

    goto :goto_2f

    .line 240
    .end local v0    # "e":Ljava/io/IOException;
    .end local v1    # "in":Ljava/io/InputStream;
    .restart local v2    # "in":Ljava/io/InputStream;
    :catchall_35
    move-exception v4

    move-object v1, v2

    .end local v2    # "in":Ljava/io/InputStream;
    .restart local v1    # "in":Ljava/io/InputStream;
    goto :goto_2a

    .line 238
    .end local v1    # "in":Ljava/io/InputStream;
    .restart local v2    # "in":Ljava/io/InputStream;
    :catch_38
    move-exception v4

    move-object v1, v2

    .end local v2    # "in":Ljava/io/InputStream;
    .restart local v1    # "in":Ljava/io/InputStream;
    goto :goto_1e

    .end local v1    # "in":Ljava/io/InputStream;
    .restart local v2    # "in":Ljava/io/InputStream;
    :cond_3b
    move-object v1, v2

    .end local v2    # "in":Ljava/io/InputStream;
    .restart local v1    # "in":Ljava/io/InputStream;
    goto :goto_16
.end method

.method public static getStringValue(Ljava/lang/String;)Ljava/lang/String;
    .registers 8
    .param p0, "key"    # Ljava/lang/String;

    .prologue
    .line 92
    invoke-static {}, Landroid/bb_sz/os/SystemProperties;->init()V

    .line 93
    const/4 v2, 0x0

    .line 94
    .local v2, "result":Ljava/lang/String;
    invoke-static {p0}, Landroid/bb_sz/os/SystemProperties;->changed(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    .line 95
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

    .line 96
    invoke-static {}, Landroid/bb_sz/os/SystemProperties;->getMockProp()Ljava/util/Properties;

    move-result-object v3

    const-string v4, ""

    invoke-virtual {v3, v1, v4}, Ljava/util/Properties;->getProperty(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    .line 99
    :cond_2d
    if-nez v2, :cond_42

    sget-object v3, Landroid/bb_sz/os/SystemProperties;->mNativeGetString_1:Ljava/lang/reflect/Method;

    if-eqz v3, :cond_42

    .line 101
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

    .line 110
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

    .line 111
    :cond_68
    return-object v2

    .line 102
    .end local v2    # "result":Ljava/lang/String;
    :catch_69
    move-exception v0

    .line 103
    .local v0, "e":Ljava/lang/Exception;
    invoke-virtual {v0}, Ljava/lang/Exception;->printStackTrace()V

    .line 104
    const-string v2, ""

    .line 105
    .restart local v2    # "result":Ljava/lang/String;
    sget-boolean v3, Landroid/bb_sz/os/SystemProperties;->debug:Z

    if-eqz v3, :cond_42

    .line 106
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
    .line 115
    invoke-static {}, Landroid/bb_sz/os/SystemProperties;->init()V

    .line 116
    move-object v2, p1

    .line 117
    .local v2, "result":Ljava/lang/String;
    invoke-static {p0}, Landroid/bb_sz/os/SystemProperties;->changed(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    .line 118
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

    .line 119
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

    .line 122
    :cond_3e
    if-eqz v2, :cond_5c

    invoke-virtual {v2, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_5c

    sget-object v3, Landroid/bb_sz/os/SystemProperties;->mNativeGetString_2:Ljava/lang/reflect/Method;

    if-eqz v3, :cond_5c

    .line 124
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

    .line 133
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

    .line 134
    :cond_82
    return-object v2

    .line 125
    .end local v2    # "result":Ljava/lang/String;
    :catch_83
    move-exception v0

    .line 126
    .local v0, "e":Ljava/lang/Exception;
    invoke-virtual {v0}, Ljava/lang/Exception;->printStackTrace()V

    .line 127
    move-object v2, p1

    .line 128
    .restart local v2    # "result":Ljava/lang/String;
    sget-boolean v3, Landroid/bb_sz/os/SystemProperties;->debug:Z

    if-eqz v3, :cond_5c

    .line 129
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
    .line 29
    :try_start_0
    sget-object v2, Landroid/bb_sz/os/SystemProperties;->mCls:Ljava/lang/Class;

    if-nez v2, :cond_c

    .line 30
    const-string v2, "android.os.SystemProperties"

    invoke-static {v2}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v2

    sput-object v2, Landroid/bb_sz/os/SystemProperties;->mCls:Ljava/lang/Class;
    :try_end_c
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_c} :catch_74

    .line 36
    .local v1, "ignored":Ljava/lang/Exception;
    :cond_c
    :goto_c
    sget-object v2, Landroid/bb_sz/os/SystemProperties;->mCls:Ljava/lang/Class;

    if-eqz v2, :cond_22

    .line 38
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

    .line 43
    :cond_22
    :goto_22
    sget-object v2, Landroid/bb_sz/os/SystemProperties;->mCls:Ljava/lang/Class;

    .end local v1    # "ignored":Ljava/lang/Exception;
    if-eqz v2, :cond_3d

    .line 45
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

    .line 50
    :cond_3d
    :goto_3d
    sget-object v2, Landroid/bb_sz/os/SystemProperties;->mCls:Ljava/lang/Class;

    if-eqz v2, :cond_58

    .line 52
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

    .line 57
    :cond_58
    :goto_58
    sget-object v2, Landroid/bb_sz/os/SystemProperties;->mCls:Ljava/lang/Class;

    if-eqz v2, :cond_73

    .line 59
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

    .line 64
    :cond_73
    :goto_73
    return-void

    .line 32
    :catch_74
    move-exception v1

    .line 33
    .restart local v1    # "ignored":Ljava/lang/Exception;
    const/4 v2, 0x0

    sput-object v2, Landroid/bb_sz/os/SystemProperties;->mCls:Ljava/lang/Class;

    goto :goto_c

    .line 39
    :catch_79
    move-exception v0

    .line 40
    .local v0, "e":Ljava/lang/NoSuchMethodException;
    invoke-virtual {v0}, Ljava/lang/NoSuchMethodException;->printStackTrace()V

    goto :goto_22

    .line 46
    .end local v0    # "e":Ljava/lang/NoSuchMethodException;
    .end local v1    # "ignored":Ljava/lang/Exception;
    :catch_7e
    move-exception v0

    .line 47
    .restart local v0    # "e":Ljava/lang/NoSuchMethodException;
    invoke-virtual {v0}, Ljava/lang/NoSuchMethodException;->printStackTrace()V

    goto :goto_3d

    .line 53
    .end local v0    # "e":Ljava/lang/NoSuchMethodException;
    :catch_83
    move-exception v0

    .line 54
    .restart local v0    # "e":Ljava/lang/NoSuchMethodException;
    invoke-virtual {v0}, Ljava/lang/NoSuchMethodException;->printStackTrace()V

    goto :goto_58

    .line 60
    .end local v0    # "e":Ljava/lang/NoSuchMethodException;
    :catch_88
    move-exception v0

    .line 61
    .restart local v0    # "e":Ljava/lang/NoSuchMethodException;
    invoke-virtual {v0}, Ljava/lang/NoSuchMethodException;->printStackTrace()V

    goto :goto_73
.end method

.method public static native_get(Ljava/lang/String;)Ljava/lang/String;
    .registers 4
    .param p0, "key"    # Ljava/lang/String;

    .prologue
    .line 72
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

    .line 73
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
    .line 77
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

    .line 78
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
    .line 82
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

    .line 83
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
    .line 87
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

    .line 88
    :cond_26
    invoke-static {p0, p1, p2}, Landroid/bb_sz/os/SystemProperties;->getLongValue(Ljava/lang/String;J)J

    move-result-wide v0

    return-wide v0
.end method
