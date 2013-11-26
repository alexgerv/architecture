'''
Created on 2013-09-26

@author: Philippe Bouillon
'''

from random import choice, randint
import datetime
import os

NUMBER_OF_MATCHES = 1000

def createMatchInformations():
    sportList = ['Football', 'Soccer', 'Badminton', 'Rugby', 'Volleyball', 'Basketball', 'Diving', 'Swimming', 'Golf']
    venueList = ['Stade Telus', 'Montreal', 'Sherbrooke']
    teamList = ['ULaval', 'UQAM', 'USherbrooke']
    sexList = ['MEN', 'WOMEN', 'MIXED']
    month = randint(1, 12)
    day = randint(1, 28)
    hour = randint(7, 22)
    minute = choice([0,30])
    date = datetime.datetime(2013, month, day, hour, minute)
    
    sport = choice(sportList)
    venue = choice(venueList)
    venueListPosition = venueList.index(venue)
    homeTeam = teamList[venueListPosition]
    teamList.remove(homeTeam)
    visitorTeam = choice(teamList)
    sex = choice(sexList)
    stringDate = date.strftime('%b %d, %Y %I:%M:%S %p')
    
    matchInformation = '"matchInformation":{{"sport":"{}","venue":"{}","date":"{}","homeTeam":"{}","visitorTeam":"{}","sex":"{}"}}'.format(sport, venue, stringDate, homeTeam, visitorTeam, sex)
    return matchInformation

def createSections(matchInformation):
    sections = '"sections":['
    sectionList = ['A', 'B', 'C', 'D', 'E', 'F', 'G']
    admissionType = ['GENERAL', 'SEATED']
    numberOfSection = randint(1, 7)
    ticketID = 1
    
    for section in range(numberOfSection):
        name = sectionList[section]
        price = randint(10, 100)
        admission = choice(admissionType)
    
        sections += '{"tickets":['
        ticketNumber = randint(20, 100)
        while ticketNumber > 0:
            sections += createTicket(ticketID, True, admission, price, matchInformation)
            sections += ','
            ticketID += 1
            ticketNumber -= 1
        sections = sections.rstrip(',')
        
        sections += '],"name":"{}","price":{},"admissionType":"{}",{}}},'.format(name, price, admission, matchInformation)
    sections = sections.rstrip(',')

    sections += ']'
    return sections

def createTicket(index, availability, admission, price, matchInformation):
    return '{{"ID":{},"admissionType":"{}", "price":{}, "available":{},{}}}'.format(index, admission, price, availability, matchInformation)

if __name__ == "__main__":

    for count in range(NUMBER_OF_MATCHES):
        matchInformation = createMatchInformations() 
        sections = createSections(matchInformation)
        match = '{{{},{}}}'.format(matchInformation, sections)

        number = str((count+1)).zfill(4)
        path = '%s.json' % (number)
        path = os.path.join('matches', path);
        
        directory = os.path.dirname(path)
        if not os.path.exists(directory):
            os.makedirs(directory)

        f = open(path,'w+')
        f.write(match)
