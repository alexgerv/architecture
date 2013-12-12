'''
Created on 2013-09-26
#######################################################
# @author: Philippe Bouillon # <!-- The VERY best --> #
#######################################################
'''

from random import choice, randint
import datetime
import os

NUMBER_OF_MATCHES = 1000

def createMatchInformations(folder, fileName):
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

    folder[0] = venue
    fileName[0] = date.strftime('%Y-%m-%d %Hh%M')

    return matchInformation

def createSections(matchInformation, ticketID):
    sections = os.linesep + '"sections":['
    sectionList = ['A', 'B', 'C', 'D', 'E', 'F', 'G']
    admissionType = ['GENERAL', 'SEATED']
    numberOfSection = randint(1, 7)
    
    
    for section in range(numberOfSection):
        name = sectionList[section]
        price = randint(10, 100)
        admission = choice(admissionType)
    
        sections += os.linesep + '{"tickets":['
        ticketNumber = randint(100, 200)
        while ticketNumber > 0:
            sections += createTicket(ticketID[0], "AVAILABLE", admission, price, matchInformation)
            sections += ','
            ticketID[0] = ticketID[0] + 1
            ticketNumber -= 1
        sections = sections.rstrip(',')
        
        sections += '],"name":"{}","price":{},"admissionType":"{}",{}}},'.format(name, price, admission, matchInformation)
    sections = sections.rstrip(',')

    sections += ']'
    return sections

def createTicket(index, availability, admission, price, matchInformation):
    return os.linesep + '{{"ID":{},"admissionType":"{}", "price":{}, "availability":"{}",{}}}'.format(index, admission, price, availability, matchInformation)
    
    
if __name__ == "__main__":
    ticketID = [1]
    path = ""
    for count in range(NUMBER_OF_MATCHES):
        folder = ['']
        fileName = ['']
        matchInformation = createMatchInformations(folder, fileName) 
        sections = createSections(matchInformation, ticketID)
        match = '{{{},{}}}'.format(matchInformation, sections)
        path = fileName[0]
        path = os.path.join(folder[0], path)
        path = os.path.join('matches', path);
        
        directory = os.path.dirname(path)
        if not os.path.exists(directory):
            os.makedirs(directory)

        f = open(path,'w')
        f.write(match)
        f.close()
