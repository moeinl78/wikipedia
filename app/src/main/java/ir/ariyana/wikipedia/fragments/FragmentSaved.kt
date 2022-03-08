package ir.ariyana.wikipedia.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.ariyana.wikipedia.MainSecondActivity
import ir.ariyana.wikipedia.adapter.AdapterSave
import ir.ariyana.wikipedia.data.Explore
import ir.ariyana.wikipedia.databinding.FragmentSavedBinding
import ir.ariyana.wikipedia.interf.DataEvent

class FragmentSaved : Fragment(), DataEvent {

    lateinit var binding : FragmentSavedBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSavedBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val data = arrayListOf(
            Explore("https://cdn1.epicgames.com/b30b6d1b4dfd4dcc93b5490be5e094e5/offer/RDR2476298253_Epic_Games_Wishlist_RDR2_2560x1440_V01-2560x1440-2a9ebe1f7ee202102555be202d5632ec.jpg", "Red Dead Redemption 2", "Survival game", "Red Dead Redemption 2[a] is a 2018 action-adventure game developed and published by Rockstar Games. The game is the third entry in the Red Dead series and is a prequel to the 2010 game Red Dead Redemption. The story is set in 1899 and follows the exploits of outlaw Arthur Morgan, a member of the Van der Linde gang, in a fictionalized representation of the Western, Midwestern, and Southern United States. Arthur must deal with the decline of the Wild West whilst attempting to survive against government forces, rival gangs, and other adversaries. The game's epilogue follows fellow gang member John Marston, the protagonist of Red Dead Redemption.", "The game is presented through both first and third-person perspectives, and the player may freely roam in its interactive open world. Gameplay elements include shootouts, heists, hunting, horseback riding, interacting with non-player characters, and maintaining the character's honor rating through moral choices and deeds. A bounty system similar to the \"wanted\" system from the Grand Theft Auto franchise governs the response of law enforcement and bounty hunters to crimes committed by the player.\n" +
                    "\n" +
                    "The game's development lasted over eight years, beginning soon after Red Dead Redemption's release, and it became one of the most expensive video games ever made. Rockstar co-opted all of its studios into one large team to facilitate development. They drew influence from real locations as opposed to film or art, focused on creating an accurate reflection of the time with the game's characters and world. The game was Rockstar's first built specifically for eighth generation consoles, having tested their technical capabilities while porting Grand Theft Auto V to the platforms. The game's soundtrack features an original score composed by Woody Jackson and several vocal tracks produced by Daniel Lanois.\n" +
                    "\n" +
                    "Red Dead Redemption 2 was released for the PlayStation 4 and Xbox One in October 2018, and for Microsoft Windows and Stadia in November 2019. Red Dead Online, the online multiplayer mode of the game, was released as a beta version in November 2018 before a full release in May 2019. Widely anticipated and marketed before release, Red Dead Redemption 2 broke several records and had the second-biggest launch in the history of entertainment, generating US\$725 million in sales from its opening weekend and exceeding the lifetime sales of Red Dead Redemption in two weeks. It received critical acclaim, with praise directed at its story, characters, open world, graphics, music, and level of detail; some criticism was directed at its control scheme and emphasis on realism over player freedom. Considered an example of video games as an art form, it won year-end accolades including Game of the Year awards from several gaming publications. It is among the best-selling video games with over 43 million copies shipped.", false, "-", false),
            Explore("https://app.akharinkhabar.ir/images/2021/08/01/269dbcff-3ef5-4ad4-86ca-becaad895632.jpeg", "The Last of Us Part II", "Video game", "The Last of Us Part II is a 2020 action-adventure game developed by Naughty Dog and published by Sony Interactive Entertainment for the PlayStation 4. Set five years after The Last of Us (2013), the game focuses on two playable characters in a post-apocalyptic United States whose lives intertwine: Ellie, who sets out for revenge after suffering a tragedy, and Abby, a soldier who becomes involved in a conflict between her militia and a religious cult. The game is played from the third-person perspective and allows the player to fight human enemies and cannibalistic zombie-like creatures with firearms, improvised weapons, and stealth.", "Development of The Last of Us Part II began in 2014, soon after the release of The Last of Us Remastered. Neil Druckmann returned as creative director, co-writing the story with Halley Gross. The themes of revenge and retribution were inspired by Druckmann's experiences growing up in Israel. Ashley Johnson reprises her role as Ellie, while Laura Bailey was cast as Abby. Their performances included the simultaneous recording of motion and voice. The developers pushed the technical capabilities of the PlayStation 4 during development. Gustavo Santaolalla returned to compose and perform the score. Development reportedly included a crunch schedule of 12-hour workdays.\n" +
                    "\n" +
                    "Following some delays, partly due to the COVID-19 pandemic, The Last of Us Part II was released on June 19, 2020. It received critical acclaim, with praise for its gameplay, audio design, score, performances, characters, and visual fidelity, though its narrative and themes divided critics. It was the subject of review bombing on Metacritic, with some player criticism directed at particular scenes, chapters, and characters; discourse surrounding the game became adversarial. Part II is one of the best-selling PlayStation 4 games and the fastest-selling PlayStation 4 exclusive, with over four million units sold in its release weekend. It holds the record for most Game of the Year awards, and received multiple other accolades from awards shows and gaming publications.", false, "-", true),
            Explore("https://image.api.playstation.com/vulcan/img/rnd/202008/2617/gCLof29n2eeFxVRGpo90xW1A.jpg", "God of War", "Video game series", "God of War[b] is an action-adventure game developed by Santa Monica Studio and published by Sony Interactive Entertainment (SIE). It was released worldwide on April 20, 2018, for the PlayStation 4 with a Microsoft Windows version released on January 14, 2022. The game is the eighth installment in the God of War series, the eighth chronologically, and the sequel to 2010's God of War III.", "Unlike previous games, which were loosely based on Greek mythology, this installment is loosely inspired by Norse mythology, with the majority of it set in ancient Scandinavia in the realm of Midgard. For the first time in the series, there are two protagonists: Kratos, the former Greek God of War who remains the only playable character, and his young son, Atreus. Following the death of Kratos' second wife, Atreus' mother, they journey to fulfill her request that her ashes be spread at the highest peak of the nine realms. Kratos keeps his troubled past a secret from Atreus, who is unaware of his divine nature. Along their journey, they come into conflict with monsters and gods of the Norse world.\n" +
                    "\n" +
                    "Described by creative director Cory Barlog as a reimagining of the franchise, a major gameplay change is that Kratos makes prominent use of a magical battle axe instead of his signature double-chained blades. God of War also uses an over-the-shoulder free camera, with the game in one shot, as opposed to the fixed cinematic camera of the previous entries. The game also includes role-playing video game elements, and Kratos' son Atreus provides assistance in combat. The majority of the original game's development team worked on God of War and designed it to be accessible and grounded. A separate short text-based game, A Call from the Wilds, was released in February 2018 and follows Atreus on his first adventure.\n" +
                    "\n" +
                    "God of War received universal acclaim for its story, world design, art direction, music, graphics, combat system, and characters, in particular the dynamic between Kratos and Atreus. Many reviewers felt it had successfully revitalized the series without losing the core identity of its predecessors. It received a number of perfect review scores, tying it with the original God of War (2005) as the highest-rated game in the series, as well as one of the highest-rated PlayStation 4 games on the review aggregator Metacritic. Among other awards and nominations, God of War was awarded Game of the Year by numerous media outlets and award shows. The game performed well commercially, selling over five million copies within a month of its release and over 19.5 million by August 2021, making it the best-selling PlayStation 4 game as well as the best-selling game in the series. A novelization was released in August 2018, followed by a prequel comic series published from November 2018 to February 2019. A sequel titled God of War Ragnarök is scheduled for release in 2022 for the PlayStation 4 and PlayStation 5.", false, "-", false),
            Explore("https://c.opencritic.com/cdn-cgi/image/h=625,w=1110,fit=crop,q=85,f=auto/images/games/1538/2LsuMHYG8VDwDODvwS.jpg", "Uncharted 4: A Thief's End", "Video Game", "Uncharted 4: A Thief's End is a 2016 action-adventure game developed by Naughty Dog and published by Sony Computer Entertainment. It is the fourth main entry in the Uncharted series. Set several years after the events of Uncharted 3: Drake's Deception, players control Nathan Drake, a former treasure hunter coaxed out of retirement by his presumed-dead brother Samuel. With Nathan's longtime partner, Victor Sullivan, they search for clues for the location of Henry Avery's long-lost treasure. A Thief's End is played from a third-person perspective, and incorporates platformer elements. Players solve puzzles and use firearms, melee combat, and stealth to combat enemies. In the online multiplayer mode, up to ten players engage in co-operative and competitive modes.", "Development of Uncharted 4 began in 2011, soon after the release of Uncharted 3. It was led by creative director Amy Hennig and game director Justin Richmond. Development was hampered in 2014 due to Hennig and Richmond's departure from Naughty Dog; they were replaced by Neil Druckmann and Bruce Straley. The team sought to incorporate elements of open-world gameplay, with larger levels to encourage free-roaming exploration and combat. The relationship between Nathan and Elena was central, and Naughty Dog attempted to humanize them more than in previous games. A Thief's End was released in May 2016 for the PlayStation 4. It was the first Naughty Dog game developed specifically for the PlayStation 4. The team took advantage of the hardware to process larger dynamic environments.\n" +
                    "\n" +
                    "Following its announcement in November 2013, A Thief's End was widely anticipated. The game was acclaimed by critics, praising its gameplay, narrative, emotional depth, visuals, and multiplayer. Several reviewers found the game a worthy conclusion to Nathan's story. It won several year-end accolades, including Game of the Year awards from several gaming publications, critics, and game award ceremonies. With over 15 million copies sold, it is the highest-selling Uncharted game and one of the best-selling PlayStation 4 games. A standalone expansion, Uncharted: The Lost Legacy, was released in 2017. A remastered version, alongside The Lost Legacy as part of the Uncharted: Legacy of Thieves Collection, was released in January 2022 for PlayStation 5, and will be released later in 2022 for Microsoft Windows.", false, "-", true),
        )

        val dataSet : ArrayList<Explore> = arrayListOf()
        data.map { item ->
            if(item.isSaved) {
                dataSet.add(item)
            }
        }

        val adapter = AdapterSave(dataSet, this)
        binding.saveFragmentRecyclerView.adapter = adapter
        binding.saveFragmentRecyclerView.layoutManager = LinearLayoutManager(parentFragment?.context, RecyclerView.VERTICAL, false)
    }

    override fun onPostClicked(post: Explore) {

        val intent = Intent(activity, MainSecondActivity::class.java)
        intent.putExtra(POST_DATA, post)
        startActivity(intent)
    }
}