package com.home.ann;

import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.util.TreePath;
import com.sun.source.util.Trees;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Set;

@SupportedAnnotationTypes("com.home.ann.HelloWorld")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class HelloWorldProcessor extends AbstractProcessor {

    private Trees trees;

    @Override public void init(final ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        this.trees = Trees.instance(processingEnv);
    }

    @Override
    public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
        final String simpleClassName = this.getClass().getSimpleName();
        this.processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE,
                simpleClassName + " Annotation Processor started");

        roundEnv.getElementsAnnotatedWith(HelloWorld.class).stream()
                .filter(element -> element instanceof TypeElement)
                .forEach(element -> this.doSomeThing(element));

        this.processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE,
                simpleClassName + "Annotation Processor end");
        return true;
    }

    private void doSomeThing(final Element element) {
        final TreePath treesPath = this.trees.getPath(element);
        final CompilationUnitTree compilationUnit = treesPath.getCompilationUnit();

    }

}
