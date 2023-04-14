package com.raks.pvcreator.data.dao

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.raks.pvcreator.data.FakePvDatabase
import com.raks.pvcreator.data.entity.*
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@SmallTest
@HiltAndroidTest
class PvDaoTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var db: FakePvDatabase

    private lateinit var dao:     PvDao
    private lateinit var fakeDao: FakePvDao

    @Before
    fun setup() {
        hiltRule.inject()

        dao     = db.pvDao()
        fakeDao = db.fakePvDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun when_getAllCards_is_called_Expect_list_of_cards() = runTest {
        val card = Card(1, "card")

        fakeDao.insertCard(card)

        val cards = dao.getAllCards()

        assertThat(cards).contains(card)
    }

    @Test
    fun when_getAllItems_is_called_Expect_list_of_items() = runTest {
        val card = Card(1, "card")
        val item = Item(1, "item", "code", card.id)

        fakeDao.insertCard(card)
        fakeDao.insertItem(item)

        val items = dao.getAllItems(card.id)

        assertThat(items).contains(item)
    }

    @Test
    fun when_getAllVariants_is_called_Expect_list_of_variants() = runTest {
        val card    = Card(1, "card")
        val item    = Item(1, "item", "code", card.id)
        val variant = Variant(1, "variant", "code", item.id, null)

        fakeDao.insertCard(card)
        fakeDao.insertItem(item)
        fakeDao.insertVariant(variant)

        val variants = dao.getAllVariants(item.id)

        assertThat(variants).contains(variant)
    }

    @Test
    fun when_getAllWildcards_is_called_Expect_list_of_wildcards() = runTest {
        val card     = Card(1, "card")
        val item     = Item(1, "item", "code", card.id)
        val wildcard = Wildcard(1, "wildcard", "code", item.id, null)

        fakeDao.insertCard(card)
        fakeDao.insertItem(item)
        fakeDao.insertWildcard(wildcard)

        val wildcards = dao.getAllWildcards(item.id)

        assertThat(wildcards).contains(wildcard)
    }

    @Test
    fun when_getAllDurations_is_called_Expect_list_of_durations() = runTest {
        val duration = Duration(1, "duration", "code")

        fakeDao.insertDuration(duration)

        val durations = dao.getAllDurations()

        assertThat(durations).contains(duration)
    }

}
