package br.com.vr.vrminiautorizador;

import br.com.vr.vrminiautorizador.controllers.CardControllerTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@SuiteDisplayName("Test")
@Suite
@SelectClasses({CardControllerTest.class})
public class VrMiniAutorizadorApplicationTests {}
