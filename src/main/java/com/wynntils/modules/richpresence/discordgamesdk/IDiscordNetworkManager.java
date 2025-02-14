package com.wynntils.modules.richpresence.discordgamesdk;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Callback;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.ptr.LongByReference;
import com.wynntils.modules.richpresence.discordgamesdk.enums.EDiscordResult;
import com.wynntils.modules.richpresence.discordgamesdk.options.DiscordGameSDKOptions;

/**
 * <i>native declaration : line 498</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> , <a href="http://rococoa.dev.java.net/">Rococoa</a>, or <a href="http://jna.dev.java.net/">JNA</a>.
 */
public class IDiscordNetworkManager extends Structure implements DiscordGameSDKOptions {

    /** C type : get_peer_id_callback* */
    public IDiscordNetworkManager.get_peer_id_callback get_peer_id;
    /** C type : flush_callback* */
    public IDiscordNetworkManager.flush_callback flush;
    /** C type : open_peer_callback* */
    public IDiscordNetworkManager.open_peer_callback open_peer;
    /** C type : update_peer_callback* */
    public IDiscordNetworkManager.update_peer_callback update_peer;
    /** C type : close_peer_callback* */
    public IDiscordNetworkManager.close_peer_callback close_peer;
    /** C type : open_channel_callback* */
    public IDiscordNetworkManager.open_channel_callback open_channel;
    /** C type : close_channel_callback* */
    public IDiscordNetworkManager.close_channel_callback close_channel;
    /** C type : send_message_callback* */
    public IDiscordNetworkManager.send_message_callback send_message;

    public interface get_peer_id_callback extends Callback, DiscordGameSDKOptions {
        void apply(IDiscordNetworkManager manager, LongByReference peer_id);
    };

    public interface flush_callback extends Callback, DiscordGameSDKOptions {
        EDiscordResult apply(IDiscordNetworkManager manager);
    };

    public interface open_peer_callback extends Callback, DiscordGameSDKOptions {
        EDiscordResult apply(IDiscordNetworkManager manager, long peer_id, String route_data);
    };

    public interface update_peer_callback extends Callback, DiscordGameSDKOptions {
        EDiscordResult apply(IDiscordNetworkManager manager, long peer_id, String route_data);
    };

    public interface close_peer_callback extends Callback, DiscordGameSDKOptions {
        EDiscordResult apply(IDiscordNetworkManager manager, long peer_id);
    };

    public interface open_channel_callback extends Callback, DiscordGameSDKOptions {
        EDiscordResult apply(IDiscordNetworkManager manager, long peer_id, byte channel_id, byte reliable);
    };

    public interface close_channel_callback extends Callback, DiscordGameSDKOptions {
        EDiscordResult apply(IDiscordNetworkManager manager, long peer_id, byte channel_id);
    };

    public interface send_message_callback extends Callback, DiscordGameSDKOptions {
        EDiscordResult apply(IDiscordNetworkManager manager, long peer_id, byte channel_id, Pointer data, int data_length);
    };

    public IDiscordNetworkManager() {
        super();
    }

    protected List<String> getFieldOrder() {
        return Arrays.asList("get_peer_id", "flush", "open_peer", "update_peer", "close_peer", "open_channel", "close_channel", "send_message");
    }

    /**
     * @param get_peer_id   C type : get_peer_id_callback*<br>
     * @param flush         C type : flush_callback*<br>
     * @param open_peer     C type : open_peer_callback*<br>
     * @param update_peer   C type : update_peer_callback*<br>
     * @param close_peer    C type : close_peer_callback*<br>
     * @param open_channel  C type : open_channel_callback*<br>
     * @param close_channel C type : close_channel_callback*<br>
     * @param send_message  C type : send_message_callback*
     */
    public IDiscordNetworkManager(IDiscordNetworkManager.get_peer_id_callback get_peer_id, IDiscordNetworkManager.flush_callback flush, IDiscordNetworkManager.open_peer_callback open_peer, IDiscordNetworkManager.update_peer_callback update_peer, IDiscordNetworkManager.close_peer_callback close_peer, IDiscordNetworkManager.open_channel_callback open_channel, IDiscordNetworkManager.close_channel_callback close_channel, IDiscordNetworkManager.send_message_callback send_message) {
        super();
        this.get_peer_id = get_peer_id;
        this.flush = flush;
        this.open_peer = open_peer;
        this.update_peer = update_peer;
        this.close_peer = close_peer;
        this.open_channel = open_channel;
        this.close_channel = close_channel;
        this.send_message = send_message;
    }

    public IDiscordNetworkManager(Pointer peer) {
        super(peer);
    }

    public static class ByReference extends IDiscordNetworkManager implements Structure.ByReference {

    };

    public static class ByValue extends IDiscordNetworkManager implements Structure.ByValue {

    };
}
