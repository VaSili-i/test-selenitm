package com.example.demo;

import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarResponse;
import net.lightbody.bmp.mitm.manager.ImpersonatingMitmManager;
import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class BrowserProxy {



    public static WebDriver getDriverCapProxy(BrowserMobProxyServer browserMobProxy) {
        // BrowserMobProxyServer browserMobProxy = BrowserProxy.getBrowserMobProxy();

        Proxy proxy = ClientUtil.createSeleniumProxy(browserMobProxy);
        proxy.setNoProxy("<-loopback>");
        proxy.setHttpProxy("localhost:" + browserMobProxy.getPort());
        proxy.setProxyType(Proxy.ProxyType.MANUAL);
        proxy.setSslProxy("localhost:" + browserMobProxy.getPort());
        DesiredCapabilities cap = DesiredCapabilities.chrome();

        ChromeOptions options = new ChromeOptions();

        System.setProperty("webdriver.chrome.driver", "/home/vasilii/data/lexpro-gitlab/lexpro/src/main/resources/chromedriver");

        options.addArguments("--ignore-certificate-errors");
        // options.addArguments( "--disable-gpu", "--headless", "--no-sandbox");
        cap.setCapability(ChromeOptions.CAPABILITY, options);
        cap.setCapability(CapabilityType.PROXY, proxy);

        WebDriver driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    public static BrowserMobProxyServer getBrowserMobProxy() {
        BrowserMobProxyServer browserMobProxy = new BrowserMobProxyServer();

        browserMobProxy.setTrustAllServers(true);
        browserMobProxy.setMitmManager(ImpersonatingMitmManager.builder().trustAllServers(true).build());
        browserMobProxy.start(3001);

        System.out.println("Port Started On: " + browserMobProxy.getPort());

        browserMobProxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT, CaptureType.RESPONSE_HEADERS);

        return browserMobProxy;
    }

    public static void stopProxyAndDriver(BrowserMobProxyServer browserMobProxy, WebDriver driver) {
        driver.quit();
        browserMobProxy.stop();
    }

    public static Object getBodyByUrl(String url, Har har) {
        List<HarEntry> list = har.getLog().getEntries();
        String harBody = null;

        for (HarEntry harEntry : list) {
            String urls = harEntry.getRequest().getUrl();

            if(Objects.equals(urls, url)){
                HarResponse harResponse = harEntry.getResponse();
                harResponse.getContent();
                harBody = harResponse.getContent().getText();
            }
        }

        return harBody;
    }

    public static void startListenHTTP(BrowserMobProxyServer browserMobProxy) {
        browserMobProxy.newHar("HAR");
    }
}