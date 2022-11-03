package com.inventory.prosta.bot;

import com.inventory.prosta.bot.util.DataSource;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junitpioneer.jupiter.SetEnvironmentVariable;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.event.annotation.PrepareTestInstance;

@SetEnvironmentVariable(key = "COUNTRY_CODE", value = "RU")
@SetEnvironmentVariable(key = "LANGUAGE_CODE", value = "ru")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public abstract class BaseUnitTest {


}
