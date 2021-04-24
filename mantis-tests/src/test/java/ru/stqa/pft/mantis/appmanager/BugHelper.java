package ru.stqa.pft.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.axis.Handler;
import org.apache.axis.SimpleChain;
import org.apache.axis.SimpleTargetedChain;
import org.apache.axis.client.AxisClient;
import org.apache.axis.configuration.SimpleProvider;
import org.apache.axis.transport.http.HTTPSender;
import org.apache.axis.transport.http.HTTPTransport;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import ru.stqa.pft.mantis.model.Issue;

import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.AssertJUnit.assertEquals;

public class BugHelper {
  private ApplicationManager app;

  public BugHelper(ApplicationManager app) {
    this.app = app;
  }

  //которая должна через Remote API получать из баг-трекера информацию о баг-репорте
  // с заданным идентификатором, и возвращать значение false или true в зависимости от того,
  // помечен он как исправленный или нет
  public boolean isIssueOpen(int issueId) throws IOException, ServiceException {

    boolean result = true; //по умолчанию баг не close

    //если учет багов ведется в мантис
    if (app.getProperty("bugtracker").equals("mantis")) {
      MantisConnectPortType mc = getMantisConnect();
      IssueData createdIssueData = mc.mc_issue_get("administrator", "root", BigInteger.valueOf(issueId));
      if (createdIssueData.getStatus().getName().equals("closed")) {
        result = false;
      }
    //если учет багов ведется в Bugify
    } else if (app.getProperty("bugtracker").equals("Bugify")) {
      String json = getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues/" + issueId + ".json"))
              .returnContent().asString();
      JsonElement parsed = new JsonParser().parse(json);
      JsonElement issue = parsed.getAsJsonObject().get("issues");
      Set<Issue> newIssue = new Gson().fromJson(issue, new TypeToken<Set<Issue>>() {}.getType());
      String status = newIssue.stream().map((p) -> (p).getStatus()).findFirst().get().toString();
      if (status.equals("Deleted") | status.equals("Closed")) {
        result = false;
      }
    } else System.out.println("bugtracker not selected");
    
    return result;
  }


  private MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
    SimpleProvider clientConfig = new SimpleProvider();
    AxisLogHandler logHandler = new AxisLogHandler();
    SimpleChain reqHandler = new SimpleChain();
    SimpleChain respHandler = new SimpleChain();
    reqHandler.addHandler(logHandler);
    respHandler.addHandler(logHandler);
    Handler pivot = new HTTPSender();
    Handler transport = new SimpleTargetedChain(reqHandler, pivot, respHandler);
    clientConfig.deployTransport(HTTPTransport.DEFAULT_TRANSPORT_NAME, transport);
    MantisConnectLocator locator = new MantisConnectLocator();
    locator.setEngineConfiguration(clientConfig);
    locator.setEngine(new AxisClient(clientConfig));
    return locator.getMantisConnectPort(new URL(app.getProperty("web.baseUrl") + "/api/soap/mantisconnect.php"));
  }

  private Executor getExecutor() {
    return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
  }

}
