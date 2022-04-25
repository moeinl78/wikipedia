package ir.ariyana.wikipedia.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import ir.ariyana.wikipedia.ui.MainSecondActivity
import ir.ariyana.wikipedia.R
import ir.ariyana.wikipedia.ui.adapter.AdapterExplore
import ir.ariyana.wikipedia.model.data.Explore
import ir.ariyana.wikipedia.model.local.ExploreDao
import ir.ariyana.wikipedia.model.local.WikiDB
import ir.ariyana.wikipedia.databinding.FragmentExploreBinding
import ir.ariyana.wikipedia.model.interf.DataEvent
import ir.ariyana.wikipedia.presenter.explore.ContractExplore
import ir.ariyana.wikipedia.presenter.explore.PresenterExplore

const val POST_DATA = "post_data"

class FragmentExplore : Fragment(), DataEvent, ContractExplore.View {

    private lateinit var binding : FragmentExploreBinding
    private lateinit var exploreDAO : ExploreDao
    private lateinit var adapter : AdapterExplore
    private lateinit var presenterExplore : PresenterExplore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExploreBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        exploreDAO = WikiDB.createDatabase(binding.root.context).exploreDao

        presenterExplore = PresenterExplore(binding.root.context)
        presenterExplore.onAttach(this)

        val sharedPreferences = this.activity?.getSharedPreferences("app", Context.MODE_PRIVATE)
        if (sharedPreferences != null) {
            if (sharedPreferences.getBoolean("app_first_run", true)){
                appFirstRun()
                sharedPreferences
                    .edit()
                    .putBoolean("app_first_run", false)
                    .apply()
            }
        }
        receiveDBItems()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenterExplore.onDetach()
    }

    private fun appFirstRun() {

        val data = arrayListOf(
            Explore(null, "https://cdn1.epicgames.com/b30b6d1b4dfd4dcc93b5490be5e094e5/offer/RDR2476298253_Epic_Games_Wishlist_RDR2_2560x1440_V01-2560x1440-2a9ebe1f7ee202102555be202d5632ec.jpg", "Red Dead Redemption 2", "Survival game", "Red Dead Redemption 2[a] is a 2018 action-adventure game developed and published by Rockstar Games. The game is the third entry in the Red Dead series and is a prequel to the 2010 game Red Dead Redemption. The story is set in 1899 and follows the exploits of outlaw Arthur Morgan, a member of the Van der Linde gang, in a fictionalized representation of the Western, Midwestern, and Southern United States. Arthur must deal with the decline of the Wild West whilst attempting to survive against government forces, rival gangs, and other adversaries. The game's epilogue follows fellow gang member John Marston, the protagonist of Red Dead Redemption.", "The game is presented through both first and third-person perspectives, and the player may freely roam in its interactive open world. Gameplay elements include shootouts, heists, hunting, horseback riding, interacting with non-player characters, and maintaining the character's honor rating through moral choices and deeds. A bounty system similar to the \"wanted\" system from the Grand Theft Auto franchise governs the response of law enforcement and bounty hunters to crimes committed by the player.\n" +
                    "\n" +
                    "The game's development lasted over eight years, beginning soon after Red Dead Redemption's release, and it became one of the most expensive video games ever made. Rockstar co-opted all of its studios into one large team to facilitate development. They drew influence from real locations as opposed to film or art, focused on creating an accurate reflection of the time with the game's characters and world. The game was Rockstar's first built specifically for eighth generation consoles, having tested their technical capabilities while porting Grand Theft Auto V to the platforms. The game's soundtrack features an original score composed by Woody Jackson and several vocal tracks produced by Daniel Lanois.\n" +
                    "\n" +
                    "Red Dead Redemption 2 was released for the PlayStation 4 and Xbox One in October 2018, and for Microsoft Windows and Stadia in November 2019. Red Dead Online, the online multiplayer mode of the game, was released as a beta version in November 2018 before a full release in May 2019. Widely anticipated and marketed before release, Red Dead Redemption 2 broke several records and had the second-biggest launch in the history of entertainment, generating US\$725 million in sales from its opening weekend and exceeding the lifetime sales of Red Dead Redemption in two weeks. It received critical acclaim, with praise directed at its story, characters, open world, graphics, music, and level of detail; some criticism was directed at its control scheme and emphasis on realism over player freedom. Considered an example of video games as an art form, it won year-end accolades including Game of the Year awards from several gaming publications. It is among the best-selling video games with over 43 million copies shipped.", false, "-", false),
            Explore(null, "https://app.akharinkhabar.ir/images/2021/08/01/269dbcff-3ef5-4ad4-86ca-becaad895632.jpeg", "The Last of Us Part II", "Video game", "The Last of Us Part II is a 2020 action-adventure game developed by Naughty Dog and published by Sony Interactive Entertainment for the PlayStation 4. Set five years after The Last of Us (2013), the game focuses on two playable characters in a post-apocalyptic United States whose lives intertwine: Ellie, who sets out for revenge after suffering a tragedy, and Abby, a soldier who becomes involved in a conflict between her militia and a religious cult. The game is played from the third-person perspective and allows the player to fight human enemies and cannibalistic zombie-like creatures with firearms, improvised weapons, and stealth.", "Development of The Last of Us Part II began in 2014, soon after the release of The Last of Us Remastered. Neil Druckmann returned as creative director, co-writing the story with Halley Gross. The themes of revenge and retribution were inspired by Druckmann's experiences growing up in Israel. Ashley Johnson reprises her role as Ellie, while Laura Bailey was cast as Abby. Their performances included the simultaneous recording of motion and voice. The developers pushed the technical capabilities of the PlayStation 4 during development. Gustavo Santaolalla returned to compose and perform the score. Development reportedly included a crunch schedule of 12-hour workdays.\n" +
                    "\n" +
                    "Following some delays, partly due to the COVID-19 pandemic, The Last of Us Part II was released on June 19, 2020. It received critical acclaim, with praise for its gameplay, audio design, score, performances, characters, and visual fidelity, though its narrative and themes divided critics. It was the subject of review bombing on Metacritic, with some player criticism directed at particular scenes, chapters, and characters; discourse surrounding the game became adversarial. Part II is one of the best-selling PlayStation 4 games and the fastest-selling PlayStation 4 exclusive, with over four million units sold in its release weekend. It holds the record for most Game of the Year awards, and received multiple other accolades from awards shows and gaming publications.", false, "-", false),
            Explore(null, "https://image.api.playstation.com/vulcan/img/rnd/202008/2617/gCLof29n2eeFxVRGpo90xW1A.jpg", "God of War", "Video game series", "God of War[b] is an action-adventure game developed by Santa Monica Studio and published by Sony Interactive Entertainment (SIE). It was released worldwide on April 20, 2018, for the PlayStation 4 with a Microsoft Windows version released on January 14, 2022. The game is the eighth installment in the God of War series, the eighth chronologically, and the sequel to 2010's God of War III.", "Unlike previous games, which were loosely based on Greek mythology, this installment is loosely inspired by Norse mythology, with the majority of it set in ancient Scandinavia in the realm of Midgard. For the first time in the series, there are two protagonists: Kratos, the former Greek God of War who remains the only playable character, and his young son, Atreus. Following the death of Kratos' second wife, Atreus' mother, they journey to fulfill her request that her ashes be spread at the highest peak of the nine realms. Kratos keeps his troubled past a secret from Atreus, who is unaware of his divine nature. Along their journey, they come into conflict with monsters and gods of the Norse world.\n" +
                    "\n" +
                    "Described by creative director Cory Barlog as a reimagining of the franchise, a major gameplay change is that Kratos makes prominent use of a magical battle axe instead of his signature double-chained blades. God of War also uses an over-the-shoulder free camera, with the game in one shot, as opposed to the fixed cinematic camera of the previous entries. The game also includes role-playing video game elements, and Kratos' son Atreus provides assistance in combat. The majority of the original game's development team worked on God of War and designed it to be accessible and grounded. A separate short text-based game, A Call from the Wilds, was released in February 2018 and follows Atreus on his first adventure.\n" +
                    "\n" +
                    "God of War received universal acclaim for its story, world design, art direction, music, graphics, combat system, and characters, in particular the dynamic between Kratos and Atreus. Many reviewers felt it had successfully revitalized the series without losing the core identity of its predecessors. It received a number of perfect review scores, tying it with the original God of War (2005) as the highest-rated game in the series, as well as one of the highest-rated PlayStation 4 games on the review aggregator Metacritic. Among other awards and nominations, God of War was awarded Game of the Year by numerous media outlets and award shows. The game performed well commercially, selling over five million copies within a month of its release and over 19.5 million by August 2021, making it the best-selling PlayStation 4 game as well as the best-selling game in the series. A novelization was released in August 2018, followed by a prequel comic series published from November 2018 to February 2019. A sequel titled God of War Ragnarök is scheduled for release in 2022 for the PlayStation 4 and PlayStation 5.", false, "-", false),
            Explore(null, "https://c.opencritic.com/cdn-cgi/image/h=625,w=1110,fit=crop,q=85,f=auto/images/games/1538/2LsuMHYG8VDwDODvwS.jpg", "Uncharted 4: A Thief's End", "Video Game", "Uncharted 4: A Thief's End is a 2016 action-adventure game developed by Naughty Dog and published by Sony Computer Entertainment. It is the fourth main entry in the Uncharted series. Set several years after the events of Uncharted 3: Drake's Deception, players control Nathan Drake, a former treasure hunter coaxed out of retirement by his presumed-dead brother Samuel. With Nathan's longtime partner, Victor Sullivan, they search for clues for the location of Henry Avery's long-lost treasure. A Thief's End is played from a third-person perspective, and incorporates platformer elements. Players solve puzzles and use firearms, melee combat, and stealth to combat enemies. In the online multiplayer mode, up to ten players engage in co-operative and competitive modes.", "Development of Uncharted 4 began in 2011, soon after the release of Uncharted 3. It was led by creative director Amy Hennig and game director Justin Richmond. Development was hampered in 2014 due to Hennig and Richmond's departure from Naughty Dog; they were replaced by Neil Druckmann and Bruce Straley. The team sought to incorporate elements of open-world gameplay, with larger levels to encourage free-roaming exploration and combat. The relationship between Nathan and Elena was central, and Naughty Dog attempted to humanize them more than in previous games. A Thief's End was released in May 2016 for the PlayStation 4. It was the first Naughty Dog game developed specifically for the PlayStation 4. The team took advantage of the hardware to process larger dynamic environments.\n" +
                    "\n" +
                    "Following its announcement in November 2013, A Thief's End was widely anticipated. The game was acclaimed by critics, praising its gameplay, narrative, emotional depth, visuals, and multiplayer. Several reviewers found the game a worthy conclusion to Nathan's story. It won several year-end accolades, including Game of the Year awards from several gaming publications, critics, and game award ceremonies. With over 15 million copies sold, it is the highest-selling Uncharted game and one of the best-selling PlayStation 4 games. A standalone expansion, Uncharted: The Lost Legacy, was released in 2017. A remastered version, alongside The Lost Legacy as part of the Uncharted: Legacy of Thieves Collection, was released in January 2022 for PlayStation 5, and will be released later in 2022 for Microsoft Windows.", false, "-", false),
            Explore(null, "https://i.ytimg.com/vi/Y-JWFa3FioY/maxresdefault.jpg", "Uncharted 3: Drake's Deception", "VIDEO GAME", "Uncharted 3: Drake's Deception is a 2011 action-adventure game developed by Naughty Dog and published by Sony Computer Entertainment. It is the third main entry in the Uncharted series, and was released in November 2011 for the PlayStation 3. Set two years after Among Thieves (2009), the single-player story follows Nathan Drake and his mentor Victor Sullivan as they search for the legendary lost city of Iram of the Pillars while battling a mercenary group led by Sullivan's former employer, Katherine Marlowe.", "Development for Uncharted 3: Drake's Deception began in 2010. For the first time in the company's history, Naughty Dog split into two teams; while one team developed Uncharted 3, the other developed The Last of Us (2013). Development was approached with incorporating locations distinct from the series' previous entries, with the team deciding on deserts and urban areas, drawing inspiration for the plot from the life of archaeologist T. E. Lawrence. Naughty Dog sought to upgrade the game's openness and realism, increasing the volume of motion capture and voice acting, and conducting field research for better visual environments and sounds. The development team also aimed to improve the multiplayer system, introducing new competitive and co-operative modes, while the game is also notable for being one of the first to carry the new online PlayStation Network Pass feature.", true, "780", true),
            Explore(null, "https://images.gog-statics.com/29775073d3b97f926235212b7b2953ad8acb8d22173b2897a04477950fffe659_product_card_v2_mobile_slider_639.jpg", "Witcher 3: Wild Hunt", "VIDEO GAME", "The Witcher 3: Wild Hunt[a] is an action role-playing game developed by Polish developer CD Projekt Red, and first published in 2015. It is the sequel to the 2011 game The Witcher 2: Assassins of Kings and the third and final main game in The Witcher video game series, played in an open world with a third-person perspective. The games are based on The Witcher series of fantasy novels written by Andrzej Sapkowski.", "The game takes place in a fictional fantasy world based on Slavic mythology. Players control Geralt of Rivia, a monster slayer for hire known as a Witcher, and search for his adopted daughter, who is on the run from the otherworldly Wild Hunt. Players battle the game's many dangers with weapons and magic, interact with non-player characters, and complete quests to acquire experience points and gold, which are used to increase Geralt's abilities and purchase equipment. The game's story has three possible endings, determined by the player's choices at key points in the narrative.\n" +
                    "\n" +
                    "Development began in 2011 and lasted for three and a half years. Central and Northern European cultures formed the basis of the game's world. REDengine 3 enabled the developer to create a complex story without compromising the game's open world. The music was primarily composed by Marcin Przybyłowicz and performed by the Brandenburg State Orchestra.", true, "512", false),
            Explore(null, "https://images.gog-statics.com/8355e657a19311b158a3553a154e109199d6991c7791a20c3305af1f84d15ed7.jpg", "Witcher 2:Assassination of the kings", "VIDEO GAME", "The Witcher 2: Assassins of Kings (Polish: Wiedźmin 2: Zabójcy królów) is a 2011 action role-playing video game developed by CD Projekt Red, based on The Witcher series of fantasy novels by Andrzej Sapkowski. It is the sequel to the 2007 game The Witcher and the second main installment in The Witcher's video game series. It was released for Microsoft Windows, Xbox 360, OS X, and Linux.[7]", "The gameplay of The Witcher 2 is a marked departure from that of its predecessor. Combat, for instance, is much more complex, with additions in the form of abilities to lay traps and aim and throw ranged weapons. The protagonist, Geralt, has an improved offensive and defensive arsenal, with a wide range of melee and ranged weapons, armor, bombs, traps, and secondary weapons such as hatchets and shovels. Upgrades are divided into four distinct paths: an initial training path, which includes generalized upgrades for various core abilities and must be invested in before the other paths can be accessed; swordsmanship, which improves Geralt's sword-fighting abilities; alchemy, which includes perks such as reducing the negative effects of potions; and magic, which improves Witcher Signs.\n" +
                    "\n" +
                    "The Witcher 2 includes a stealth mode in certain parts of the game, where players must remain undetected as they make their way to a certain objective. Players have the option of stunning enemies if Geralt manages to get behind them, but the player may choose to take a less subtle approach and engage the guards in combat.", false, "-", true),
            Explore(null, "https://cdn2.unrealengine.com/ac2-gamename-store-landscape-2560x1440-2560x1440-aa944fa0e8c6.jpg", "Assassin's Creed II", "VIDEO GAME", "Assassin's Creed II is a 2009 action-adventure video game developed by Ubisoft Montréal and published by Ubisoft.[1] It is the second major installment in the Assassin's Creed series, and the sequel to 2007's Assassin's Creed. The game was first released on the PlayStation 3 and Xbox 360 in November 2009, and was later made available on Microsoft Windows in March 2010 and OS X in October 2010. Several minor game-related features could be redeemed on Uplay and three downloadable expansion packs, including two story expansions, were released on Xbox Live and later to other platforms. A three-part live-action short film, titled Assassin's Creed: Lineage, which serves as a prequel to the main story and stars most of the game's cast, was produced and released on YouTube to promote it ahead of release.", "The game's plot is set in a fictional history of real-world events and follows the millennia-old struggle between the Assassins, who fight to preserve peace and free will, and the Templars, who desire peace through control. The framing story is set in the 21st century and follows Desmond Miles as he relives the genetic memories of his ancestor, Ezio Auditore da Firenze, to uncover the mysteries left behind by an ancient race known as the First Civilization in the hope of ending the Assassin-Templar conflict. The main narrative takes place at the height of the Renaissance in Italy from 1476 to 1499, and follows Ezio's journey as an Assassin while seeking revenge against those responsible for the death of his father and brothers. Gameplay focuses on using Ezio's combat, stealth, and parkour abilities to defeat enemies and explore the environment. The game features a large open world comprising several Italian cities, including Florence, Venice, Monteriggioni, San Gimignano, and Forlì, all of which have been accurately recreated to fit the game's historical setting.\n" +
                    "\n" +
                    "Using a newly updated Anvil game engine, Assassin's Creed II began development shortly after the release of Assassin's Creed. The game received praise from video game publications for its Renaissance setting, narrative, characters, map design and visuals, as well as the improvements over its predecessor. It has sold more than nine million copies, and is considered to be one of the best video games ever made. The PC version was met with some criticism in relation to the digital rights management system, and thus had the always-online DRM permanently removed. The game spawned two direct sequels: Assassin's Creed: Brotherhood (2010) and Assassin's Creed: Revelations (2011), and a spin-off, Assassin's Creed II: Discovery (2009). Remastered versions of Assassin's Creed II, Brotherhood, and Revelations were released as part of the Assassin's Creed: The Ezio Collection compilation for the PlayStation 4 and Xbox One on November 15, 2016, and for the Nintendo Switch on February 17, 2022.", false, "-", false),
            Explore(null, "https://images.alphacoders.com/205/thumb-1920-205673.jpg", "Assassin's Creed: Brotherhood", "VIDEO GAME", "Assassin's Creed: Brotherhood is a 2010 action-adventure video game developed by Ubisoft Montreal and published by Ubisoft.[1][2] It is the third major installment in the Assassin's Creed series, and the second chapter in the \"Ezio Trilogy\", as a direct sequel to 2009's Assassin's Creed II. The game was first released on the PlayStation 3 and Xbox 360 in November and December 2010 and was later made available on Microsoft Windows in March and June 2011.", "The plot is set in a fictional history of real-world events and follows the millennia-old struggle between the Assassins, who fight to preserve peace and free will, and the Templars, who desire peace through control. The framing story is set in the 21st century and features series protagonist Desmond Miles who, using a machine known as the Animus, relives the memories of his Assassin ancestor, Ezio Auditore da Firenze, to find a way to avert the 2012 apocalypse. The main story spans the years 1500–1507 and continues from the events of Assassin's Creed II, as Ezio takes the fight against the Templars (led by the influential Borgia family) to Rome, where he attempts to rebuild the Assassin Brotherhood in Italy and liberate the city from the Borgias' control.\n" +
                    "\n" +
                    "Brotherhood features an open world and is played from the third-person perspective, with a primary focus on using Desmond's and Ezio's combat, climbing and stealth abilities to eliminate targets and explore the environment. As Ezio, players can freely explore Rome to advance the narrative, or complete a variety of side missions unrelated to the main storyline. The game introduces a multiplayer component to the series, in which players assume the role of a Templar in training. A number of downloadable content (DLC) packs were released to support Brotherhood, including The Da Vinci Disappearance, a story expansion set during the events of the single-player campaign.\n" +
                    "\n" +
                    "Upon release, the game received critical acclaim, with praise directed at its setting, new content, gameplay improvements over its predecessor and the new multiplayer mode. The narrative was also positively received, although it was generally seen as inferior to that of Assassin's Creed II. The game won multiple awards including a BAFTA award for Best Action Game. It was also commercially successful, shipping 7.2 million units by May 2011. The final installment in the Ezio Trilogy, Assassin's Creed: Revelations, was released in November 2011. A remastered version of Brotherhood, along with Assassin's Creed II and Revelations, was released as part of The Ezio Collection for the PlayStation 4 and Xbox One on November 15, 2016, and for the Nintendo Switch on February 17, 2022.", false, "-", true),
            Explore(null, "https://cdn-ext.fanatical.com/production/product/1280x720/df07b610-5441-4cbf-99c2-1c3e8fdaf6d9.jpeg", "Assassin's Creed: Revelations", "VIDEO GAME", "Assassin's Creed: Revelations is a 2011 action-adventure video game developed by Ubisoft Montreal and published by Ubisoft. It is the fourth major installment in the Assassin's Creed series, and a direct sequel to 2010's Assassin's Creed: Brotherhood, concluding the \"Ezio Trilogy\". The game was released on PlayStation 3, Xbox 360, and Microsoft Windows in November and December 2011. A remastered version of Revelations, along with Assassin's Creed II and Brotherhood, was released as part of The Ezio Collection for the PlayStation 4 and Xbox One on November 15, 2016, and for the Nintendo Switch on February 17, 2022.", "The plot is set in a fictional history of real-world events and follows the millennia-old struggle between the Assassins, who fight to preserve peace and free will, and the Templars, who desire peace through control. The framing story is set in the 21st century and features the series protagonist Desmond Miles who, after falling into a coma during the events of Brotherhood, must relive the memories of his ancestors through the Animus device in order to awaken and find a way to avert the 2012 apocalypse. The main story spans the years 1511–1512, and follows an aged Ezio Auditore da Firenze (the protagonist of the trilogy) as he travels to Constantinople to find five keys needed to unlock a vault built three centuries ago by Altaïr Ibn-LaʼAhad (the protagonist of the first game in the series). While searching for the keys, Ezio becomes caught in a feud between the Ottomans and the Byzantines, and discovers that the Templars are also attempting to unlock Altaïr's vault and its secrets.\n" +
                    "\n" +
                    "Revelations features an open world and is played from the third-person perspective, with a primary focus on using Ezio's and Altaïr's combat, climbing and stealth abilities to eliminate targets and explore the environment. Ezio, who the player controls throughout the majority of the game, can freely explore Constantinople and complete side missions unrelated to the main storyline. Altaïr is playable in a smaller capacity, as he is featured only in a series of flashback missions set in Masyaf from 1189 to 1257. Desmond's section of the game is different from anything that has come before in the series, and involves first-person platforming within the Animus. The multiplayer mode returns from Assassin's Creed: Brotherhood, and has been expanded with additional maps, characters, and game modes.\n" +
                    "\n" +
                    "The game was released with multiple editions, some of which featured exclusive limited-time content. Most notably, several editions included an animated short film, titled Assassin's Creed: Embers, which serves as a proper conclusion to Ezio's story, and which was later re-released as a free download on the PlayStation Store. Downloadable content (DLC) released for the game includes new maps and characters for the multiplayer mode, and a story expansion titled The Lost Archive, which adds more platforming levels to the modern-day.", false, "-", false),
        )
        data.map { item ->
            exploreDAO.insertPost(item)
        }
    }

    private fun receiveDBItems() {

        val data = ArrayList(exploreDAO.receivePosts())
        adapter = AdapterExplore(data, this)
        binding.fragmentExploreRecyclerView.adapter = adapter
        binding.fragmentExploreRecyclerView.layoutManager = LinearLayoutManager(parentFragment?.context, RecyclerView.VERTICAL, false)
    }

    override fun onPostClicked(post: Explore) {
        val intent = Intent(activity, MainSecondActivity::class.java)
        intent.putExtra(POST_DATA, post)
        startActivity(intent)
    }

    override fun onBookMarkClicked(post: Explore) {
        // receive right item from database
        val postDB = exploreDAO.receivePost(post.id!!)
        postDB.isSaved = !postDB.isSaved
        exploreDAO.updatePost(postDB)

        if(postDB.isSaved) {
            activity?.let {
                Snackbar
                    .make(it.findViewById(android.R.id.content), "You can find this post in saved section!", Snackbar.LENGTH_SHORT)
                    .setBackgroundTint(resources.getColor(R.color.blue))
                    .setTextColor(resources.getColor(R.color.white))
                    .show()
            }
        }
        else {
            activity?.let {
                Snackbar
                    .make(it.findViewById(android.R.id.content), "Post removed from the save section!", Snackbar.LENGTH_SHORT)
                    .setBackgroundTint(resources.getColor(R.color.blue))
                    .setTextColor(resources.getColor(R.color.white))
                    .show()
            }
        }

        // restart dataset to show saved items
        val dataSet = ArrayList(exploreDAO.receivePosts())
        adapter.setData(dataSet)
    }

    override fun receivePosts(posts: List<Explore>) {

    }
}