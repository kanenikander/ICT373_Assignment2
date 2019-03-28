package ICT373_Ass2;

/**  
* TITLE: ICT373, Assignment 2
* @author Kane Nikander (31566009)
* DATE: 1/05/2018
* FILENAME: ICT373_Ass2.java
* PURPOSE: A program that allows for the creation, editing, opening and saving of family tree data through a graphical user interface.
* ASSUMPTIONS: 
* 1. Person can have no more than one spouse.
* 2. Spouse is of the opposite gender.
* 3. Incestuous relationships cannot be added through the GUI and will not be represented (accurately) in the produced diagram.
* 4. Family beyond the root persons mother and father can be added but will not display in the produced diagram.
* 5. Gender cannot be changed on person who currently has a spouse. Removing the spouse will allow this.
* 6. Invalid inputs (such as adding text to number, etc) will not be added. Data will remain as is.
* 7. If two spouses have children they will not be merged. It is necessary to start with only one partner with children or link partners before adding children. This does not pose an issue with the GUI program. 
*/ 
public class ICT373_Ass2 {
    
    public static void main(String[] args) {
                
        //INITIAL TEST DATA (GoT family tree)

        //HOUSE STARK
        Person p1 = new Person("Rickard","Stark","Stark",'m',"Lord Rickard Stark was the Lord of Winterfell, the Warden of the North and head of House Stark until he was executed along with his eldest son and heir Brandon by the Mad King, Aerys II Targaryen. He was the father of Brandon, Eddard, Lyanna and Benjen Stark. Both Rickard Stark and the Mad King are grandfathers to Jon Snow.",new Address(1,"Winterfell Castle","Winterfell",1234),new Relationship());
        Person p2 = new Person("Lyarra","","Stark",'f',"",new Address(),new Relationship());
        Person p3 = new Person("Brandon","Stark","Stark",'m',"Brandon Stark was the heir of Rickard Stark, the Lord of Winterfell, and the older brother of Eddard, Lyanna and Benjen Stark. He was killed by strangulation by the Mad King when he protested the kidnapping of Lyanna by Prince Rhaegar Targaryen. His father Rickard died along with him by wildfire.",new Address(1,"Winterfell Castle","Winterfell",1234),new Relationship());
        Person p4 = new Person("Catelyn","Tully","Stark",'f',"Catelyn Stark, née Tully, was born into House Tully as the daughter of Hoster Tully, the Lord Paramount of the Trident, and sister of Lysa and Edmure Tully. She married into House Stark through her marriage to Eddard Stark, though she was originally intended for Brandon Stark. Together, she and Eddard had five children: Robb, Sansa, Arya, Bran, and Rickon. Catelyn was a devoted mother and was fiercely protective of her children.",new Address(10,"Riverrun Keep","Riverrun",33),new Relationship());
        Person p5 = new Person("Eddard","Stark","Stark",'m',"Lord Eddard Stark, also known as Ned Stark, was the head of House Stark, the Lord of Winterfell, Lord Paramount and Warden of the North, and later Hand of the King to King Robert I Baratheon. He was the older brother of Benjen, Lyanna and the younger brother of Brandon Stark. He is the father of Robb, Sansa, Arya, Bran and Rickon by his wife, Catelyn Tully, and uncle of Jon Snow, who he raised as his bastard son. He was a dedicated husband and father, a loyal friend and an honorable lord.\n" +
"\n" +
"Eddard's execution and revealing the illegitimacy of Cersei's children was the spark of the War of the Five Kings between Joffrey Baratheon, Robb, Renly Baratheon, Stannis Baratheon and Balon Greyjoy, being posthumously responsible for the involvement of four of the kings in this war.",new Address(1,"Winterfell Castle","Winterfell",1234),new Relationship());
        Person p6 = new Person("Lyanna","Stark","Stark",'f',"Princess Lyanna Stark was the daughter of Lord Rickard Stark and sister of Brandon, Eddard, and Benjen Stark. She went on to be the wife of Rhaegar Targaryen, the Prince of Dragonstone, and mother to his son.Her alleged kidnapping by Prince Rhaegar Targaryen contributed to the outbreak of Robert's Rebellion. Lyanna dies near the very end of the rebellion shortly after giving birth to her son, revealed to be Jon Snow, who is her child with Rhaegar Targaryen. Before she passes away, Lyanna tells her brother Ned the name of her baby is Aegon Targaryen and pleads with Ned that he'll promise to keep her child safe, fearing that her formerly betrothed, Robert Baratheon, would have her son killed if he ever found out the truth that his father was Rhaegar. To protect his sister's son, Ned brings his infant nephew to Winterfell, renames him 'Jon'[1] and claims him as his own bastard son, raising him as his child alongside his trueborn children. ",new Address(1,"Winterfell Castle","Winterfell",1234),new Relationship());
        Person p7 = new Person("Rhaegar","Targaryen","Targaryen",'m',"Prince Rhaegar Targaryen was the eldest son and heir to King Aerys II Targaryen, Prince of Dragonstone. He was the older brother of Viserys and Daenerys Targaryen and the husband of Elia Martell, with whom he had two children: Rhaenys and Aegon Targaryen.Secretly, however, Rhaegar annulled his marriage with Elia and married Lyanna Stark. Rhaegar's alleged \"abduction\" of Lyanna sparked Robert's Rebellion, as Lyanna was betrothed to Robert Baratheon. Robert killed Rhaegar at the climactic Battle of the Trident, and then deposed the Targaryen dynasty. Lyanna's brother Eddard Stark found her soon afterward, as she was dying from childbirth. She had just given birth to her son with Rhaegar and begged her brother to keep her baby safe. To protect the child from Robert and others who sought the destruction of House Targaryen, Eddard claimed his sister's son as his bastard child who he fathered during the war: Jon Snow.",new Address(1,"Red Keep","The Crownlands",666),new Relationship());
        Person p8 = new Person("Benjen","Stark","Stark",'m',"Benjen Stark was the First Ranger of the Night's Watch. He embarks on a ranging north of the Wall, and did not return. He was finally encountered again when he rescued Bran Stark and Meera Reed from wights after they escaped from the cave of the Three-Eyed Raven. Afterwards he led Bran and Meera close to the Wall but stayed behind because he could not pass through it due to his undead status. He eventually sacrificed himself to save his nephew Jon Snow by luring the army of the dead towards him.",new Address(1,"Winterfell Castle","Winterfell",1234),new Relationship());
        Person p9 = new Person("Robb","Stark","Stark",'m',"King Robb Stark was the eldest son of Lord Eddard Stark of Winterfell and his wife, Lady Catelyn. He was the older brother of Sansa, Arya, Bran, and Rickon Stark, and cousin (believed to be half-brother) of Jon Snow. He also adopted a direwolf, whom he named Grey Wind. Robb was declared King in the North, the first since his ancestor Torrhen Stark bent the knee three centuries before, during the War of the Five Kings after the execution of his father by King Joffrey Baratheon.",new Address(1,"Winterfell Castle","Winterfell",1234),new Relationship());
        Person p10 = new Person("Talisa","Nee Maegyr","Stark nee Maegyr",'f',"Queen Talisa Stark, née Maegyr, was a healer on the battlefields of the Westerlands, where she met the King in the North, Robb Stark, fell in love with him, eventually married him and became pregnant with their child. ",new Address(44,"Volantis Castle","Volantis",4444),new Relationship());
        Person p11 = new Person("Sansa","Stark","Stark",'f',"Lady Sansa Stark is the eldest daughter of Lord Eddard Stark of Winterfell and his wife Lady Catelyn, sister of Robb, Arya, Bran and Rickon Stark, and \"half-sister\" of Jon Snow.",new Address(1,"Winterfell Castle","Winterfell",1234),new Relationship());
        Person p12 = new Person("Tyrion","Lannister","Lannister",'m',"Lord Tyrion Lannister is the youngest child of Lord Tywin Lannister and younger brother of Cersei and Jaime Lannister. A dwarf, he uses his wit and intellect to overcome the prejudice he faces.\n" +
"\n" +
"His abduction by Catelyn Stark for a crime he did not commit serves as one of the catalysts of the War of the Five Kings. After escaping his captors, Tyrion is appointed by his father as acting Hand of the King to Joffrey Baratheon and successfully defends King's Landing against Stannis Baratheon at the Battle of the Blackwater, after which he is stripped of his power, demoted to Master of Coin and eventually framed for Joffrey's murder. After his champion, Oberyn Martell, dies in Tyrion's trial by combat, Tyrion flees to Essos with help from Jaime and Varys after murdering his father.\n" +
"\n" +
"In the east, he is captured by Jorah Mormont and taken to Daenerys Targaryen in Meereen, whom Varys had intended for Tyrion to meet anyway. Daenerys decides to enlist his help in reclaiming the Iron Throne. For his loyalty and service, Tyrion is named as Hand of the Queen to Daenerys before they set sail for Westeros with her new army and allies, ready to advise her when they reach her ancestral home of Dragonstone, where he acts as her strategist during her invasion of Westeros. ",new Address(1,"House Lannister","Casterly Rock",100),new Relationship());
        Person p13 = new Person("Arya","Stark","Stark",'f',"Arya Stark is the third child and second daughter of Lord Eddard Stark and his wife, Lady Catelyn Stark. After narrowly escaping the persecution of House Stark by House Lannister, Arya is trained as a Faceless Man at the House of Black and White in Braavos, and uses her new skills to bring those who have wronged her family to justice. ",new Address(1,"Winterfell Castle","Winterfell",1234),new Relationship());
        Person p14 = new Person("Bran","Stark","Stark",'m',"Brandon Stark, commonly called Bran, is the fourth child and second son of Eddard and Catelyn Stark. Bran is a warg and currently the new Three-Eyed Raven, using his supernatural gifts to assist his family in the war against the White Walkers. ",new Address(1,"Winterfell Castle","Winterfell",1234),new Relationship());
        Person p15 = new Person("Rickon","Stark","Stark",'m',"Rickon Stark was the youngest child and third son of Eddard and Catelyn Stark. ",new Address(1,"Winterfell Castle","Winterfell",1234),new Relationship());
        Person p16 = new Person("John","Aegon Targaryen","Snow",'m',"Jon Snow, born Aegon Targaryen, is the son of Lyanna Stark and Rhaegar Targaryen, the late Prince of Dragonstone. From infancy, Jon is presented as the bastard son of Lord Eddard Stark, Lyanna's brother, and raised by Eddard alongside his lawful children at Winterfell but his true parentage is kept secret from everyone, including Jon himself. In order to escape his bastard status, Jon joins the Night's Watch and is eventually chosen as Lord Commander. However, he is later murdered in a mutiny and resurrected by the Red Priestess Melisandre. Freed from his Night's Watch vows, Jon joins his cousin, Sansa Stark, in building an army and together they retake Winterfell from House Bolton, restoring House Stark's dominion over the North with Jon being declared the new King in the North. However, after successfully capturing a wight and presenting it to the Lannisters as proof that the Army of the Dead is real, Jon pledges himself and his army to Daenerys Targaryen, Rhaegar's sister, and steps down as King of the North.",new Address(1,"Winterfell Castle","Winterfell",1234),new Relationship());
        
        //HOUSE LANNISTER
        Person p17 = new Person("Tytos","Lannister","Lannister",'m',"Lord Tytos Lannister was the Lord of Casterly Rock and head of House Lannister. He was the grandfather of Cersei, Jaime, and Tyrion Lannister.",new Address(1,"House Lannister","Casterly Rock",100),new Relationship());
        Person p18 = new Person("Jeyne","Lannister","Lannister",'f',"",new Address(1,"House Lannister","Casterly Rock",100),new Relationship());
        Person p19 = new Person("Tywin","Lannister","Lannister",'m',"Lord Tywin Lannister was the head of House Lannister, Lord of Casterly Rock, Warden of the West, Lord Paramount of the Westerlands, Hand of the King for three different kings, and Protector of the Realm. He was the father of Cersei, Jaime, and Tyrion Lannister, and sole grandfather of the incest-born Joffrey, Myrcella, and Tommen Baratheon.",new Address(1,"House Lannister","Casterly Rock",100),new Relationship());
        Person p20 = new Person("Joanna","Lannister","Lannister",'f',"Joanna Lannister was the wife of Tywin Lannister and the mother of Jaime, Cersei, and Tyrion Lannister. As Tywin's first cousin, she was a Lannister before her marriage.",new Address(1,"House Lannister","Casterly Rock",100),new Relationship());
        Person p21 = new Person("Kevan","Lannister","Lannister",'m',"Ser Kevan Lannister was the younger brother of Lord Tywin Lannister and one of his most loyal and trusted officers in the Lannister armies during the War of the Five Kings.\n" +
"\n" +
"After his brother's death at the hands of his nephew Tyrion, Kevan opposes his niece Cersei's seizure of power as leader of her son King Tommen's Small Council. Refusing his appointment by Cersei as Master of War, he departs the court in protest and returns to Casterly Rock.\n" +
"\n" +
"When Cersei is arrested on various charges by the Faith Militant, Kevan returns to King's Landing and is appointed Hand of the King by Grand Maester Pycelle, until he is killed in the Destruction of the Great Sept of Baelor, arranged by Cersei herself.",new Address(1,"House Lannister","Casterly Rock",100),new Relationship());
        Person p22 = new Person("Dorna","Lannister","Lannister",'f',"Dorna Lannister née Swyft is the wife of Ser Kevan Lannister and mother of Ser Lancel Lannister, Martyn Lannister, and Willem Lannister.",new Address(1,"House Lannister","Casterly Rock",100),new Relationship());
        Person p23 = new Person("Cersei","Lannister","Lannister",'f',"Queen Cersei I Lannister is the widow of King Robert Baratheon and Queen of the Seven Kingdoms. She is the daughter of Lord Tywin Lannister, twin sister of Jaime Lannister and elder sister of Tyrion Lannister. She has an incestuous relationship with Jaime, who is secretly the father of her three children, Joffrey, Myrcella and Tommen.\n" +
"\n" +
"After the assassinations of Joffrey and Myrcella and Tommen's suicide in the wake of the destruction of the Great Sept of Baelor, Cersei assumed the throne under the name of Cersei of the House Lannister, the First of Her Name, Queen of the Andals and the First Men, Protector of the Seven Kingdoms.  ",new Address(1,"House Lannister","Casterly Rock",100),new Relationship());
        Person p24 = new Person("Jaime","Lannister","Lannister",'m',"Ser Jaime Lannister is the eldest son of Tywin, younger twin brother of Cersei, and older brother of Tyrion Lannister. He is involved in an incestuous relationship with Cersei, and unknown to most, he is the biological father of her three children, Joffrey, Myrcella, and Tommen.\n" +
"\n" +
"Jaime previously served in the Kingsguard of Aerys Targaryen, known as the Mad King, before infamously backstabbing him during the Sack of King's Landing, earning Jaime the nickname of the Kingslayer. He continued to serve in the Kingsguard of Robert Baratheon, and as Lord Commander for Robert's alleged sons Joffrey and Tommen. However, a confrontation with the Faith of the Seven led to his dismissal from the sworn order.\n" +
"\n" +
"With Cersei's ascension to the Iron Throne and in light of the death of their uncle, Ser Kevan Lannister, Jaime was appointed as the new commander of the Lannister armies. However, he left his position to honor and help the North face the White Walkers after he learned that Cersei and Euron Greyjoy plot to dishonor the truce between the Iron Throne and the alliance of House Targaryen and House Stark.",new Address(1,"House Lannister","Casterly Rock",100),new Relationship());
        //Person p25 = new Person("Tyrion","Lannister","Lannister",'m',"",new Address(1,"House Lannister","Casterly Rock",100),new Relationship());
        Person p26 = new Person("Lancel","Lannister","Lannister",'m',"Ser Lancel Lannister is the eldest son of Kevan Lannister and a nephew of Tywin Lannister. He served as a squire for King Robert Baratheon until Robert's death. Soon afterwards, he was elevated to knighthood but sustained an arrow wound during the Battle of the Blackwater that required a prolonged convalescence. During his recovery, he embraced religion and joined the movement known as the Sparrows and abandoned his family name, after which he was simply known as Brother Lancel.",new Address(1,"House Lannister","Casterly Rock",100),new Relationship());
        Person p27 = new Person("Martyn","Lannister","Lannister",'m',"Martyn Lannister along with his brother Willem, was a squire in the Lannister army. Both are younger sons of Kevan Lannister, and younger brothers of Ser Lancel Lannister.",new Address(1,"House Lannister","Casterly Rock",100),new Relationship());
        Person p28 = new Person("Willem","Lannister","Lannister",'m',"Willem Lannister, along with his brother Martyn, was a squire in the Lannister army. Both were younger sons of Ser Kevan Lannister, and younger brothers of Lancel Lannister.",new Address(1,"House Lannister","Casterly Rock",100),new Relationship());
        Person p29 = new Person("Joffrey","Baratheon","Baratheon",'m',"King Joffrey I Baratheon was the second Baratheon king to sit on the Iron Throne. Though believed by most to be the eldest son of King Robert Baratheon and Queen Cersei Lannister, Joffrey is actually a bastard born from Cersei's incestuous relationship with her twin brother, Ser Jaime Lannister of the Kingsguard. He is the older brother of Myrcella Baratheon and Tommen Baratheon, both of whom share the same parentage.",new Address(1,"House Lannister","Casterly Rock",100),new Relationship());
        Person p30 = new Person("Margaery","Tyrell","Tyrell",'f',"Queen Margaery Tyrell was the only daughter of Lord Mace Tyrell and Lady Alerie Tyrell, granddaughter of Lady Olenna Tyrell and sister of Ser Loras Tyrell. Margaery became Queen Consort through her marriage to King Joffrey Baratheon, and later his younger brother, King Tommen Baratheon, following Joffrey's death. However, her desire to become Queen, ultimately proved to be her downfall as she was eventually killed along with her brother and father when the Great Sept of Baelor was destroyed with wildfire as orchestrated by Cersei Lannister to reclaim her lost power.",new Address(8,"Castle Highgarden","Highgarden",88),new Relationship());
        Person p31 = new Person("Myrcella","Baratheon","Baratheon",'f',"Princess Myrcella Baratheon was commonly thought to be the only daughter of King Robert Baratheon and Queen Cersei Lannister. However, like her brothers, her real father is Jaime Lannister.\n" +
"\n" +
"After her upbringing in King's Landing, Myrcella was shipped to Dorne in an attempt to forge a marriage-alliance with House Martell by having her wed to Trystane Martell. However, Myrcella was later killed by Ellaria Sand, who took vengeance for the Lannister family's involvement in Oberyn Martell's death.",new Address(1,"House Lannister","Casterly Rock",100),new Relationship());
        Person p32 = new Person("Tommen","Baratheon","Baratheon",'m',"King Tommen I Baratheon was the younger brother of King Joffrey and Princess Myrcella. Though legally the son of the late King Robert Baratheon and Queen Cersei Lannister, his true father is Ser Jaime Lannister, the Queen's twin brother. His sole biological grandparents, Tywin and Joanna Lannister, were also first cousins.\n" +
"\n" +
"After his brother's death during his wedding to Margaery Tyrell, Tommen assumed the throne under the name of Tommen of the House Baratheon, the First of His Name. Many thought his reign would be a vast improvement as, unlike his brother, who was sadistic and mean-spirited, Tommen was good natured and kind towards his peers.\n" +
"\n" +
"Although originally a mere pawn in the rivalry between his mother and his new wife, Tommen found purpose with the rise of the religious Sparrows. Under the influence of their leader, he attempted to bring about a new age of joint rule between the Crown and the Faith.\n" +
"\n" +
"After witnessing the destruction of the Great Sept of Baelor, learning of the death of his beloved Queen among all the casualties, and seeing his legacy in ruins, Tommen committed suicide by intentionally falling out of an open window in the Red Keep.",new Address(1,"House Lannister","Casterly Rock",100),new Relationship());
        
        //HOUSE TARGARYEN
        Person p33 = new Person("Aegon V","Targaryen","Targaryen",'m',"King Aegon V Targaryen, also called Aegon the Unlikely, and informally known as Egg, was the fifth king of his name to sit on the Iron Throne and the fifteenth king of the Targaryen dynasty to rule the Seven Kingdoms. He was the father of the Mad King and his sister-wife Queen Rhaella; grandfather of Rhaegar, Viserys, and Daenerys Targaryen; great-grandfather of Jon Snow; and younger brother of Maester Aemon. In the books, he was dubbed \"the Unlikely\" because he was the fourth son of Maekar Targaryen, who was in turn the fourth son of Daeron II Targaryen, and thus Aegon was highly unlikely to have inherited the crown.",new Address(0,"Summerhall","The Stormlands",0),new Relationship());
        Person p34 = new Person("Duncan","Targaryen","Targaryen",'m',"Duncan Targaryen, also called Duncan the Small or the Prince of Dragonflies, was the eldest son and heir of King Aegon V Targaryen.",new Address(5,"Red Keep","King's Landing",555),new Relationship());
        Person p35 = new Person("Aerys II","Targaryen","Targaryen",'m',"King Aerys II Targaryen, popularly called \"the Mad King\", was the last member of House Targaryen to rule from the Iron Throne. He was formally styled as Aerys of the House Targaryen, the Second of His Name, King of the Andals and the First Men, Lord of the Seven Kingdoms and Protector of the Realm. Although his rule began benevolently, he succumbed to madness and was eventually deposed by Lord Robert Baratheon in a civil war. Aerys was infamously murdered by a member of his own Kingsguard, Ser Jaime Lannister, during Lord Tywin Lannister's Sack of King's Landing. This act earned Ser Jaime the nickname \"Kingslayer\" for slaying the king that he was sworn to protect as a member of the Kingsguard.\n" +
"\n" +
"Aerys's two surviving children, Viserys and Daenerys, were smuggled across the Narrow Sea to the Free Cities of Essos, while his last living grandson, Aegon, was raised as his maternal uncle Eddard Stark's bastard son, Jon Snow, to protect him from Robert's wrath. Viserys and Daenerys are intent on one day returning to Westeros to reclaim what they regard as their birthright, while Jon is focused on saving the Realm from the coming threat of the Night King.",new Address(5,"Red Keep","King's Landing",555),new Relationship());
        Person p36 = new Person("Rhaella","Targaryen","Targaryen",'f',"Queen Rhaella Targaryen was the daughter of King Aegon V Targaryen and the sister wife of King Aerys II Targaryen. She is the mother of Rhaegar, Viserys and Daenerys Targaryen. Through her eldest son, Rhaegar, she is also the paternal grandmother of Jon Snow.",new Address(5,"Red Keep","King's Landing",555),new Relationship());
        Person p37 = new Person("Daeron","Targaryen","Targaryen",'m',"Daeron Targaryen was a prince of the Targaryen dynasty. He was the younger brother of Aerys II Targaryen, uncle of Rhaegar, Viserys, and Daenerys Targaryen, and great-uncle of Jon Snow.",new Address(5,"Red Keep","King's Landing",555),new Relationship());
        Person p38 = new Person("Viserys","Targaryen","Targaryen",'m',"Viserys Targaryen was the younger brother of the late Rhaegar Targaryen, the older brother of Daenerys Targaryen, and the paternal uncle of Jon Snow. Viserys and Daenerys are the remnants of the exiled House Targaryen following Robert's Rebellion against their father Aerys II Targaryen, the Mad King. He claimed the Iron Throne as King Viserys III after his father was overthrown and killed in Robert's Rebellion but never sat on the Iron Throne before being killed by Khal Drogo.",new Address(5,"Red Keep","King's Landing",555),new Relationship());
        Person p39 = new Person("Daenerys","Targaryen","Targaryen",'f',"Queen Daenerys Targaryen, also known as Dany and Daenerys Stormborn, is the younger sister of Rhaegar Targaryen and Viserys Targaryen, the paternal aunt of Jon Snow, and the youngest child of King Aerys II Targaryen and Queen Rhaella Targaryen, who were both ousted from the Iron Throne during Robert Baratheon's rebellion.",new Address(5,"Red Keep","King's Landing",555),new Relationship());
        Person p40 = new Person("Drogo","","",'m',"Khal Drogo was a chieftain of a Dothraki khalasar. He was often referred to as \"The Great Khal",new Address(0,"Vaes Dothrak","Dothraki sea",0),new Relationship());
        Person p41 = new Person("Rhaego","","",'m',"Rhaego was the son of Khal Drogo and Khaleesi Daenerys Targaryen. He was named in honor of his uncle, Prince Rhaegar Targaryen. According to a Dothraki prophecy, he would have been the Stallion Who Mounts the World, a great khal of khals who would have united the Dothraki as one horde and used them to conquer the entire world. He was stillborn after being involved in a blood magic ritual performed by Mirri Maz Duur.",new Address(),new Relationship());
        
        //HOUSE STARK
        p1.GetFamily().UpdateSpouse(p2);
        
        p1.GetFamily().AddChild(p3);
        p1.GetFamily().AddChild(p5);
        p1.GetFamily().AddChild(p6);
        p1.GetFamily().AddChild(p8);
        
        p5.GetFamily().UpdateSpouse(p4);
        p5.GetFamily().AddChild(p9);
        p5.GetFamily().AddChild(p11);
        p5.GetFamily().AddChild(p13);
        p5.GetFamily().AddChild(p14);
        p5.GetFamily().AddChild(p15);
        p9.GetFamily().UpdateSpouse(p10);
        p11.GetFamily().UpdateSpouse(p12);
        
        p6.GetFamily().UpdateSpouse(p7);
        p6.GetFamily().AddChild(p16);
                
        //HOUSE LANNISTER
        p17.GetFamily().UpdateSpouse(p18);
        p17.GetFamily().AddChild(p19);
        p17.GetFamily().AddChild(p21);
        
        p19.GetFamily().UpdateSpouse(p20);
        p21.GetFamily().UpdateSpouse(p22);
        
        p19.GetFamily().AddChild(p24);
        p19.GetFamily().AddChild(p23);
        p19.GetFamily().AddChild(p12);
        
        p21.GetFamily().AddChild(p26);
        p21.GetFamily().AddChild(p27);
        p21.GetFamily().AddChild(p28);
        
        p23.GetFamily().AddChild(p29);
        p29.GetFamily().UpdateSpouse(p30);
        p23.GetFamily().AddChild(p31);
        p23.GetFamily().AddChild(p32);
        
        //HOUSE TARGARYEN
        p33.GetFamily().AddChild(p34);
        //p35.GetFamily().UpdateFather(p33);
        p33.GetFamily().AddChild(p35);
        p33.GetFamily().AddChild(p36);
        p33.GetFamily().AddChild(p37);
        
        //p7.GetFamily().UpdateFather(p35);
        p35.GetFamily().AddChild(p7);
        p35.GetFamily().AddChild(p38);
        p35.GetFamily().AddChild(p39);
        
        p39.GetFamily().UpdateSpouse(p40);
        p39.GetFamily().AddChild(p41);
        
        
        //INITIATING GUI
        //FamilyTree FT = new FamilyTree();
        FamilyTree FT = new FamilyTree(p1);
        
        FT.SaveFile("HOUSESTARK");
        
        //FT.OpenFile("HOUSESTARK.famtree");
        
        Interface GUI = new Interface("Family Tree");
        GUI.setSize(1000,600);
        GUI.setVisible(true);
        
    }
    
}


