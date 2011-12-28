package net.evrythng.thng.api.wrapper.tests.core;

import net.evrythng.thng.api.wrapper.tests.GlobalTest;
import net.evrythng.thng.api.wrapper.tests.CollectionsTest;
import net.evrythng.thng.api.wrapper.tests.SearchTest;
import net.evrythng.thng.api.wrapper.tests.ThngsTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses( {
	GlobalTest.class,
	ThngsTest.class,
	CollectionsTest.class, SearchTest.class
})
public class TestSuite {

}
