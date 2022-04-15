# KindleDictionaryBuilder
Create custom dictionaries for kindle readers

I created this program so I could make custom "fictionaries" based on the glossary's found at the back of some books.
Just copy out the glossary into the required format and create the custom dictionary. Then any word in the glossary can be looked up easily.

Generate the files required to create a custom Dictionary.
* dict.opf
* cover.html
* copyright.html
* content.html

Content (Word's and their definitions) are read in from a text file. The content file contains the word and any alternate version of that word 
(i.e. Full name with First and Last names being Alternate version) should be in the following format:
Word, alternate word1, alternate word2::definition

After the files are generated use Kindle previewer to create the .mobi file and transfer that to the kindle.
