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

```
Word, alternate word1, alternate word2::definition
```

After the files are generated use [Kindle previewer](https://www.amazon.com/gp/feature.html?ie=UTF8&docId=1000765261) to combine the files into a .mobi file and transfer that to the kindle.


### Resources
* [Kindle Documentation](https://s3.amazonaws.com/kindlegen/AmazonKindlePublishingGuidelines.pdf)
* [Create Custom Dictionary](https://jakemccrary.com/blog/2020/11/11/creating-a-custom-kindle-dictionary/)
