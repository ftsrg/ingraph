package ingraph.cypherparser;

import com.google.common.base.Objects;
import com.google.inject.Injector;
import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.diagnostics.Severity;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.IResourceValidator;
import org.eclipse.xtext.validation.Issue;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.slizaa.neo4j.opencypher.OpenCypherStandaloneSetup;
import org.slizaa.neo4j.opencypher.openCypher.Cypher;

@SuppressWarnings("all")
public class CypherParser {
  public static Cypher parseFile(final String fileName) {
    Logger _logger = Logger.getLogger("org.eclipse.xtext");
    _logger.setLevel(Level.ERROR);
    OpenCypherStandaloneSetup _openCypherStandaloneSetup = new OpenCypherStandaloneSetup();
    final Injector injector = _openCypherStandaloneSetup.createInjectorAndDoEMFRegistration();
    final XtextResourceSet resourceSet = injector.<XtextResourceSet>getInstance(XtextResourceSet.class);
    final String filePath = (("../../queries/" + fileName) + ".cypher");
    URI _createFileURI = URI.createFileURI(filePath);
    final Resource resource = resourceSet.getResource(_createFileURI, true);
    CypherParser.validateAndThrowError(resource);
    EList<EObject> _contents = resource.getContents();
    EObject _get = _contents.get(0);
    return ((Cypher) _get);
  }
  
  public static Cypher parseString(final String queryString) {
    try {
      Logger _logger = Logger.getLogger("org.eclipse.xtext");
      _logger.setLevel(Level.ERROR);
      OpenCypherStandaloneSetup _openCypherStandaloneSetup = new OpenCypherStandaloneSetup();
      final Injector injector = _openCypherStandaloneSetup.createInjectorAndDoEMFRegistration();
      final XtextResourceSet resourceSet = injector.<XtextResourceSet>getInstance(XtextResourceSet.class);
      URI _createURI = URI.createURI("http:/example.cypher");
      final Resource resource = resourceSet.createResource(_createURI);
      byte[] _bytes = queryString.getBytes();
      final ByteArrayInputStream in = new ByteArrayInputStream(_bytes);
      Map<Object, Object> _loadOptions = resourceSet.getLoadOptions();
      resource.load(in, _loadOptions);
      CypherParser.validateAndThrowError(resource);
      EList<EObject> _contents = resource.getContents();
      EObject _get = _contents.get(0);
      return ((Cypher) _get);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  protected static void validateAndThrowError(final Resource resource) {
    boolean seenError = false;
    String firstError = null;
    IResourceServiceProvider _resourceServiceProvider = ((XtextResource) resource).getResourceServiceProvider();
    final IResourceValidator validator = _resourceServiceProvider.getResourceValidator();
    final List<Issue> issues = validator.validate(resource, CheckMode.ALL, CancelIndicator.NullImpl);
    for (final Issue issue : issues) {
      {
        if ((Objects.equal(issue.getSeverity(), Severity.ERROR) && (!seenError))) {
          seenError = true;
          String _message = issue.getMessage();
          firstError = _message;
        }
        String _message_1 = issue.getMessage();
        InputOutput.<String>println(_message_1);
      }
    }
    if (seenError) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Error during cypher parse, the first error was: ");
      _builder.append(firstError, "");
      throw new RuntimeException(_builder.toString());
    }
  }
}
