package ch.cnlab.atm.domain;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class DataServerTest {
	DataServer ds;

	@Before
	public void setUp() throws Exception {
		ds = new DataServer();
		ds.setName("foo");
		ds.setUrl("http://foo.bar");
	}

	@Test
	public void testGetName() {
		Assert.assertEquals("foo", ds.getName());
	}

	@Test
	public void testSetName() {
		Assert.assertEquals("foo", ds.getName());
		ds.setName("bar");
		Assert.assertEquals("bar", ds.getName());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetEmptyname() {
		ds.setName("");
		Assert.fail("should throw exceptoin");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetNullname() {
		ds.setName(null);
		Assert.fail("should throw exceptoin");
	}

	@Test
	public void testGetUrl() {
		Assert.assertEquals("http://foo.bar", ds.getUrl());
	}

	@Test
	public void testSetUrl() {
		Assert.assertEquals("http://foo.bar", ds.getUrl());
		ds.setUrl("atmnb.cnlab.ch/atm");
		Assert.assertEquals("atmnb.cnlab.ch/atm", ds.getUrl());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetEmptyUrl() {
		ds.setUrl("");
		Assert.fail("should throw exceptoin");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetNullUrl() {
		ds.setUrl(null);
		Assert.fail("should throw exceptoin");
	}
}
