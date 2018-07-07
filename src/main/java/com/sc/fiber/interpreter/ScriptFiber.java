package com.sc.fiber.interpreter;

import com.sc.fiber.exception.ExceptionThrower;
import com.sc.fiber.exception.typic.WhenTimeEnum;
import com.sc.fiber.exception.typic.onload.MeetTailException;
import com.sc.fiber.interpreter.core.PartMonitor;
import com.sc.fiber.interpreter.strings.StringUnderstander;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import org.bukkit.Bukkit;
import org.bukkit.event.player.PlayerLoginEvent;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author LocyDragon
 */
public class ScriptFiber {
	public static ClassPool scriptPool = ClassPool.getDefault();

	private static final char end = '$';

	private File scriptLocation = null;

	private List<String> result = new ArrayList<>();
	private List<Part> codeParts = new ArrayList<>();

	private Part nowPart = null;
	private List<String> io = new ArrayList<>();
	private String ioHead = null;
	private boolean ioLock = false;
	private Class<?> scriptLoading = null;
	private Object instance = null;
	private HashMap<String, Method> needInvokes = new HashMap<>();

	static {
		String info =
				"    org.bukkit\n" +
						"    org.bukkit.block\n" +
						"    org.bukkit.command\n" +
						"    org.bukkit.command.defaults\n" +
						"    org.bukkit.configuration\n" +
						"    org.bukkit.configuration.file\n" +
						"    org.bukkit.configuration.serialization\n" +
						"    org.bukkit.conversations\n" +
						"    org.bukkit.enchantments\n" +
						"    org.bukkit.entity\n" +
						"    org.bukkit.entity.minecart\n" +
						"    org.bukkit.event\n" +
						"    org.bukkit.event.block\n" +
						"    org.bukkit.event.enchantment\n" +
						"    org.bukkit.event.entity\n" +
						"    org.bukkit.event.hanging\n" +
						"    org.bukkit.event.inventory\n" +
						"    org.bukkit.event.painting\n" +
						"    org.bukkit.event.player\n" +
						"    org.bukkit.event.server\n" +
						"    org.bukkit.event.vehicle\n" +
						"    org.bukkit.event.weather\n" +
						"    org.bukkit.event.world\n" +
						"    org.bukkit.generator\n" +
						"    org.bukkit.help\n" +
						"    org.bukkit.inventory\n" +
						"    org.bukkit.inventory.meta\n" +
						"    org.bukkit.map\n" +
						"    org.bukkit.material\n" +
						"    org.bukkit.metadata\n" +
						"    org.bukkit.permissions\n" +
						"    org.bukkit.plugin\n" +
						"    org.bukkit.plugin.java\n" +
						"    org.bukkit.plugin.messaging\n" +
						"    org.bukkit.potion\n" +
						"    org.bukkit.projectiles\n" +
						"    org.bukkit.scheduler\n" +
						"    org.bukkit.scoreboard\n" +
						"    org.bukkit.util\n" +
						"    org.bukkit.util.io\n" +
						"    org.bukkit.util.noise\n" +
						"    org.bukkit.util.permissions\n" +
						"java.applet \n" +
						"java.awt \n" +
						"java.awt.color \n" +
						"java.awt.datatransfer \n" +
						"java.awt.dnd \n" +
						"java.awt.event \n" +
						"java.awt.font \n" +
						"java.awt.geom \n" +
						"java.awt.im \n" +
						"java.awt.im.spi \n" +
						"java.awt.image \n" +
						"java.awt.image.renderable \n" +
						"java.awt.print \n" +
						"java.beans \n" +
						"java.beans.beancontext \n" +
						"java.io \n" +
						"java.lang \n" +
						"java.lang.annotation \n" +
						"java.lang.instrument \n" +
						"java.lang.management \n" +
						"java.lang.ref \n" +
						"java.lang.reflect \n" +
						"java.math \n" +
						"java.net \n" +
						"java.nio \n" +
						"java.nio.channels \n" +
						"java.nio.channels.spi \n" +
						"java.nio.charset \n" +
						"java.nio.charset.spi \n" +
						"java.rmi \n" +
						"java.rmi.activation \n" +
						"java.rmi.dgc \n" +
						"java.rmi.registry \n" +
						"java.rmi.server \n" +
						"java.security \n" +
						"java.security.acl \n" +
						"java.security.cert \n" +
						"java.security.interfaces \n" +
						"java.security.spec \n" +
						"java.sql \n" +
						"java.text \n" +
						"java.text.spi \n" +
						"java.util \n" +
						"java.util.concurrent \n" +
						"java.util.concurrent.atomic \n" +
						"java.util.concurrent.locks \n" +
						"java.util.jar \n" +
						"java.util.logging \n" +
						"java.util.prefs \n" +
						"java.util.regex \n" +
						"java.util.spi \n" +
						"java.util.zip \n" +
						"javax.accessibility \n" +
						"javax.activation \n" +
						"javax.activity \n" +
						"javax.annotation \n" +
						"javax.annotation.processing \n" +
						"javax.crypto \n" +
						"javax.crypto.interfaces \n" +
						"javax.crypto.spec \n" +
						"javax.imageio \n" +
						"javax.imageio.event \n" +
						"javax.imageio.metadata \n" +
						"javax.imageio.plugins.bmp \n" +
						"javax.imageio.plugins.jpeg \n" +
						"javax.imageio.spi \n" +
						"javax.imageio.stream \n" +
						"javax.jws \n" +
						"javax.jws.soap \n" +
						"javax.lang.model \n" +
						"javax.lang.model.element \n" +
						"javax.lang.model.type \n" +
						"javax.lang.model.util \n" +
						"javax.management \n" +
						"javax.management.loading \n" +
						"javax.management.modelmbean \n" +
						"javax.management.monitor \n" +
						"javax.management.openmbean \n" +
						"javax.management.relation \n" +
						"javax.management.remote \n" +
						"javax.management.remote.rmi \n" +
						"javax.management.timer \n" +
						"javax.naming \n" +
						"javax.naming.directory \n" +
						"javax.naming.event \n" +
						"javax.naming.ldap \n" +
						"javax.naming.spi \n" +
						"javax.net \n" +
						"javax.net.ssl \n" +
						"javax.print \n" +
						"javax.print.attribute \n" +
						"javax.print.attribute.standard \n" +
						"javax.print.event \n" +
						"javax.rmi \n" +
						"javax.rmi.CORBA \n" +
						"javax.rmi.ssl \n" +
						"javax.script \n" +
						"javax.security.auth \n" +
						"javax.security.auth.callback \n" +
						"javax.security.auth.kerberos \n" +
						"javax.security.auth.login \n" +
						"javax.security.auth.spi \n" +
						"javax.security.auth.x500 \n" +
						"javax.security.cert \n" +
						"javax.security.sasl \n" +
						"javax.sound.midi \n" +
						"javax.sound.midi.spi \n" +
						"javax.sound.sampled \n" +
						"javax.sound.sampled.spi \n" +
						"javax.sql \n" +
						"javax.sql.rowset \n" +
						"javax.sql.rowset.serial \n" +
						"javax.sql.rowset.spi \n" +
						"javax.swing \n" +
						"javax.swing.border \n" +
						"javax.swing.colorchooser \n" +
						"javax.swing.event \n" +
						"javax.swing.filechooser \n" +
						"javax.swing.plaf \n" +
						"javax.swing.plaf.basic \n" +
						"javax.swing.plaf.metal \n" +
						"javax.swing.plaf.multi \n" +
						"javax.swing.plaf.synth \n" +
						"javax.swing.table \n" +
						"javax.swing.text \n" +
						"javax.swing.text.html \n" +
						"javax.swing.text.html.parser \n" +
						"javax.swing.text.rtf \n" +
						"javax.swing.tree \n" +
						"javax.swing.undo \n" +
						"javax.tools \n" +
						"javax.transaction \n" +
						"javax.transaction.xa \n" +
						"javax.xml \n" +
						"javax.xml.bind \n" +
						"javax.xml.bind.annotation \n" +
						"javax.xml.bind.annotation.adapters \n" +
						"javax.xml.bind.attachment \n" +
						"javax.xml.bind.helpers \n" +
						"javax.xml.bind.util \n" +
						"javax.xml.crypto \n" +
						"javax.xml.crypto.dom \n" +
						"javax.xml.crypto.dsig \n" +
						"javax.xml.crypto.dsig.dom \n" +
						"javax.xml.crypto.dsig.keyinfo \n" +
						"javax.xml.crypto.dsig.spec \n" +
						"javax.xml.datatype \n" +
						"javax.xml.namespace \n" +
						"javax.xml.parsers \n" +
						"javax.xml.soap \n" +
						"javax.xml.stream \n" +
						"javax.xml.stream.events \n" +
						"javax.xml.stream.util \n" +
						"javax.xml.transform \n" +
						"javax.xml.transform.dom \n" +
						"javax.xml.transform.sax \n" +
						"javax.xml.transform.stax \n" +
						"javax.xml.transform.stream \n" +
						"javax.xml.validation \n" +
						"javax.xml.ws \n" +
						"javax.xml.ws.handler \n" +
						"javax.xml.ws.handler.soap \n" +
						"javax.xml.ws.http \n" +
						"javax.xml.ws.soap \n" +
						"javax.xml.ws.spi \n" +
						"javax.xml.xpath \n" +
						"org.ietf.jgss \n" +
						"org.omg.CORBA \n" +
						"org.omg.CORBA_2_3 \n" +
						"org.omg.CORBA_2_3.portable \n" +
						"org.omg.CORBA.DynAnyPackage \n" +
						"org.omg.CORBA.ORBPackage \n" +
						"org.omg.CORBA.portable \n" +
						"org.omg.CORBA.TypeCodePackage \n" +
						"org.omg.CosNaming \n" +
						"org.omg.CosNaming.NamingContextExtPackage \n" +
						"org.omg.CosNaming.NamingContextPackage \n" +
						"org.omg.Dynamic \n" +
						"org.omg.DynamicAny \n" +
						"org.omg.DynamicAny.DynAnyFactoryPackage \n" +
						"org.omg.DynamicAny.DynAnyPackage \n" +
						"org.omg.IOP \n" +
						"org.omg.IOP.CodecFactoryPackage \n" +
						"org.omg.IOP.CodecPackage \n" +
						"org.omg.Messaging \n" +
						"org.omg.PortableInterceptor \n" +
						"org.omg.PortableInterceptor.ORBInitInfoPackage \n" +
						"org.omg.PortableServer \n" +
						"org.omg.PortableServer.CurrentPackage \n" +
						"org.omg.PortableServer.POAManagerPackage \n" +
						"org.omg.PortableServer.POAPackage \n" +
						"org.omg.PortableServer.portable \n" +
						"org.omg.PortableServer.ServantLocatorPackage \n" +
						"org.omg.SendingContext \n" +
						"org.omg.stub.java.rmi \n" +
						"org.w3c.dom \n" +
						"org.w3c.dom.bootstrap \n" +
						"org.w3c.dom.events \n" +
						"org.w3c.dom.ls \n" +
						"org.xml.sax \n" +
						"org.xml.sax.ext \n" +
						"org.xml.sax.helpers ";
		Path path = Paths.get(Bukkit.class.getProtectionDomain().getCodeSource().getLocation().toString().substring(6));
		try {
			String pathS = URLDecoder.decode(path.toFile().getAbsolutePath(), "UTF-8");
			if (pathS.startsWith("le:")) {
				pathS = pathS.replace("le:", "");
			}
			scriptPool.insertClassPath(pathS);
			for (String obj : info.split("\n")) {
				String imports = obj.trim();
				scriptPool.importPackage(imports);
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	public ScriptFiber(Object result, Object result2) {
		this.result = (List<String>) result;
		this.scriptLocation = (File) result2;
	}

	public Method getNeedInvokeMethod(String eventName) {
		return needInvokes.get(eventName);
	}
    public Object getInstance() {
		return this.instance;
	}
	public void load() {
		int now = -1;
		int yet = -1;
		for (int i = 0; i < result.size(); i++) {
			now = i;
			yet = now - 1;
			String single = result.get(now);
			if (StringUnderstander.isAnnotate(single)) {
				continue;
			} else if (StringUnderstander.isEvent(single)) {
				if (this.ioLock) {
					ExceptionThrower.throwFiberException(new MeetTailException("上一个事件没有写结尾符号 '$' 就开始了" +
									"下一个事件，这是不被允许的，请在该行的上面一行(单独一行)补上符号 '$'", scriptLocation.getName(), i + 1)
							, WhenTimeEnum.ON_LOAD);
					return;
				}
				this.ioHead = single.substring(1);
				this.ioLock = true;
				continue;
			} else if (contains(single, end)) {
				this.ioLock = false;
				this.nowPart = new Part(this.ioHead, this.io);
				this.codeParts.add(this.nowPart);
				this.nowPart = null;
				this.io.clear();
				this.ioHead = null;
			} else if (this.ioLock) {
				this.io.add(single);
			}
		}
		readParts();
	}

	private boolean contains(String target, char to) {
		for (char c : target.toCharArray()) {
			if (c == to) {
				return true;
			}
		}
		return false;
	}

	public List<Part> getCodeParts() {
		return this.codeParts;
	}

	private void readParts() {
		List<Part> after = PartMonitor.monitorMe(this.codeParts, this.scriptLocation.getName());
		CtClass scriptClass = scriptPool.makeClass("com.script.fiber." + scriptLocation.getName().split("\\.")[0]);
		for (Part eachPart : after) {
			try {
				CtMethod method = CtMethod.make(eachPart.getHead() + eachPart.getBody(), scriptClass);
				scriptClass.addMethod(method);
				this.scriptLoading = scriptClass.toClass();
				this.instance = this.scriptLoading.newInstance();
				if (eachPart.des.equals("PlayerLoginEvent")) {
					this.needInvokes.put("PlayerLoginEvent",
							this.scriptLoading.getMethod(eachPart.methodName, PlayerLoginEvent.class));
				}
			} catch (Exception exc) {
				exc.printStackTrace();
			}
		}
	}
}
